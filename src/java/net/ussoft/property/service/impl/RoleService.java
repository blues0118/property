package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.FunctionDao;
import net.ussoft.property.dao.RoleDao;
import net.ussoft.property.dao.RoleFunDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Sys_function;
import net.ussoft.property.model.Sys_role;
import net.ussoft.property.model.Sys_role_fun;
import net.ussoft.property.service.IRoleService;
import net.ussoft.property.util.CommonUtils;
import net.ussoft.property.util.Constants;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleService implements IRoleService {
	
	@Resource
	private RoleDao roleDao;
	@Resource
	private AccountDao	accountDao;
	@Resource
	private RoleFunDao roleFunDao;
	@Resource
	private FunctionDao functionDao;
	@Resource
	private HttpServletRequest request;

	@Override
	public Sys_role getById(String id) {
		return roleDao.get(id);
	}

	@Override
	public List<Sys_role> list() {
		return roleDao.getAll();
	}
	
	@Override
	public PageBean<Sys_role> list(Sys_role t, PageBean<Sys_role> pageBean) {
		pageBean = roleDao.search(t, pageBean);
		return pageBean;
	}
	
	
	@Override
	public List<Sys_role> list(Sys_role role) {
		return roleDao.search(role);
	}

	@Transactional("txManager")
	@Override
	public int update(Sys_role role) {
		Sys_role tmp = roleDao.update(role);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public Sys_role insert(Sys_role role) {
		return roleDao.save(role);
	}

	@Override
	public Sys_role getByWhere(Sys_role role) {
		if (null == role) {
			return null;
		}
		List<Sys_role> roleList = roleDao.search(role);
		
		if (null != roleList && roleList.size() == 1) {
			return roleList.get(0);
		}
		return null;
	}

	@Transactional("txManager")
	@Override
	public int delete(String ids) {
		
		String[] delStrings = ids.split(",");
		for (String id : delStrings) {
			if (!id.equals("1")) {
				//删除角色时，要取消与该角色关联的帐户的关联关系。
				List<Object> values = new ArrayList<Object>();
				values.add(id);
				//移除帐户与角色的关联
				String sql = "update sys_account set roleid='' where roleid = ?";
				accountDao.update(sql, values);
				//删除角色与功能的关联
				sql = "delete from sys_role_fun where roleid =?";
				roleFunDao.del(sql, values);
				roleDao.del(id);
			}
		}
		
		return 1;
	}
	
	@Override
	public List<Sys_function> searchFunctions(String roleid) {
		if (roleid == null || roleid.equals("")) {
			return null;
		}
		//获取角色id与功能的关联list
		Sys_role_fun ref = new Sys_role_fun();
		ref.setRoleid(roleid);
		List<Sys_role_fun> role_functions = roleFunDao.search(ref);
		
		if (role_functions.size() <= 0) {
			return null;
		}
		
		List<Object> values=new ArrayList<Object>();
		StringBuilder sb=new StringBuilder();
		for (Sys_role_fun role_function : role_functions) {
			values.add(role_function.getFunid());
			sb.append("?,");
		}
		CommonUtils.deleteLastStr(sb, ",");
		
		//TODO 这里能不能直接sql in list？不用这样拼id in的字符串？
//		List values = new ArrayList();
//		values.add(idList);
		String sql = "select * from sys_function where id in ("+sb.toString()+") order by funsort asc";
		
		List<Sys_function> funList = functionDao.search(sql, values);
		
		return funList;
	}

	@Transactional("txManager")
	@Override
	public void setFunctions(List<String> funList,
			String roleid) {
		//删除roleid与功能对应的连接
		String sql = "delete from sys_role_fun where roleid=?";
		List<Object> values = new ArrayList<Object>();
		values.add(roleid);
		roleFunDao.del(sql, values);
		
		List<List<Object>> values2 = new ArrayList<List<Object>>();
		for (int i = 0; i < funList.size(); i++) {
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(UUID.randomUUID().toString());
			tmp.add(roleid);
			tmp.add(funList.get(i));
			
			values2.add(tmp);
		}
		
		roleFunDao.batchAdd("insert into sys_role_fun (id,roleid,funid) values (?,?,?)", values2);
		
	}

	@Override
	public Boolean getRoleFun(String funid) {
		Sys_account accountSession = (Sys_account) CommonUtils.getSessionAttribute(request, Constants.user_in_session);
		if (null == accountSession) {
			return false;
		}
		String roleid = "";
		roleid = accountSession.getRoleid();
		
		if (null == roleid || "".equals(roleid)) {
			return false;
		}
		
		Sys_role_fun role_fun = new Sys_role_fun();
		role_fun.setFunid(funid);
		role_fun.setRoleid(roleid);
		Sys_role_fun tmp = roleFunDao.searchOne(role_fun);
		
		if (null == tmp) {
			return false;
		}
		return true;
	}

}
