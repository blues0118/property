package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.ProjectDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Sys_function;
import net.ussoft.property.model.Sys_role_fun;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.util.CommonUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
public class ProjectService implements IProjectService {
	
	@Resource
	private ProjectDao projectDao;

	@Resource
	private AccountDao accountDao;

	@Override
	public List<Project> list() {
		return projectDao.getAll("sort");
	}

	@Override
	public PageBean<Project> list(Project t, PageBean<Project> pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> list(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional("txManager")
	@Override
	public int update(Project project) {
		Project tmp = projectDao.update(project);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}


	@Override
	public Project getByWhere(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		//TODO 删除物业项目，需要考虑1、项目下的分类、楼、单元怎么处理  2、单元的附属数据怎么处理
		if (!id.equals("1")) {
			projectDao.del(id);
		}
		
		return 1;
	}

	@Override
	public Project getById(String id) {
		return projectDao.get(id);
	}

	@Transactional("txManager")
	@Override
	public Project insert(Project project) {
		//插入时，生成项目的排序sort（获取sort字段的最大值）
		List<Object> values = new ArrayList<Object>();
		String sql = "select max(sort) from project ";
		values.clear();
		Long lo = projectDao.getLong(sql, values);
		
		if (lo > 0) {
			project.setSort(lo.intValue()+1);
		}
		
		project = projectDao.save(project);
		
		return project;
	}

	@Override
	public List<Project> list(String accountId) {
		if("".equals(accountId) || null == accountId){
			return null;
		}
		//获取账户访问的物业项目
		Sys_account account = accountDao.get(accountId);
		List<String> projectids = (List<String>)JSON.parse(account.getProjectid());
		List<Object> values=new ArrayList<Object>();
		StringBuilder sb=new StringBuilder();
		if(projectids != null){
			for(int i=0;i<projectids.size();i++){
				values.add(projectids.get(i));
				sb.append("?,");
			}
		}
		if(values.size()>0){
			CommonUtils.deleteLastStr(sb, ",");
		}else{
			values.add("1");
			sb.append("?");
		}
			
		String sql = "select * from project where id in ("+sb.toString()+") order by sort asc";
		
		List<Project> proList = projectDao.search(sql, values);
		
		return proList;
	}

}
