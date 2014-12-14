package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Sys_role;
import net.ussoft.property.service.IAccountService;
import net.ussoft.property.service.ICodeService;
import net.ussoft.property.service.IEncryService;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IRoleService;
import net.ussoft.property.util.MD5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="account")
public class AccountController extends BaseConstroller {
	
	@Resource
	private IAccountService accountService;
	@Resource
	private IEncryService encryService;
	@Resource
	private IRoleService roleService;
	@Resource
	private ICodeService codeService;
    @Resource
    private IProjectService projectService;

	
	/**
	 * 打开账户管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("SYSTEM","ACCOUNT");
		
		return new ModelAndView("/view/auth/account/list",modelMap);
	}
	
	
	/**
	 * 帐户管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Sys_account> pageBean = new PageBean<Sys_account>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("accountcode");
		
		Sys_account t = new Sys_account();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setAccountcode(searchTxt);
			t.setAccountname(searchTxt);
		}
		
		//获取数据
		pageBean = accountService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开帐户编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(String id,ModelMap modelMap) {
		Sys_account account = accountService.getById(id);
		modelMap.put("account", account);
		
		return new ModelAndView("/view/auth/account/edit", modelMap);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(Sys_account account,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (account == null ) {
			out.print(result);
			return;
		}
		int num = accountService.update(account);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 打开修改帐户密码页
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/updatepass",method=RequestMethod.GET)
	public ModelAndView updatepass(String id,ModelMap modelMap) {
		Sys_account account = accountService.getById(id);
		modelMap.put("account", account);
		
		return new ModelAndView("/view/auth/account/updatepass", modelMap);
	}
	/**
	 * 执行更新帐户密码
	 * @param id
	 * @param password
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/toupdatepass",method=RequestMethod.POST)
	public void toupdatepass(String id,String password,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		
		Sys_account account = accountService.getById(id);
		
		if (account == null || password.equals("")) {
			out.print(result);
			return;
		}
		
		account.setPassword(MD5.encode(password).toString());
		
		int num = accountService.update(account);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除。
	 * @param orgid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (ids == null || ids.equals("") ) {
			out.print(result);
			return;
		}
		
		String[] delStrings = ids.split(",");
		
		for (String id : delStrings) {
			if (!id.equals("1")) {
				accountService.delete(id);
			}
		}
		result = "success";
		
		out.print(result);
	}
	
	/**
	 * 更改帐户状态
	 * @param id
	 * @param state
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/updatestate",method=RequestMethod.POST)
	public void updatestate(String id,Integer state,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (id == null || id.equals("") || state == null || state.equals("")) {
			out.print(result);
			return;
		}
		
		//获取帐户实体
		Sys_account account = accountService.getById(id);
		if (account == null) {
			out.print(result);
			return;
		}
		account.setAccountstate(state);
		int num = accountService.update(account);
		
		if (num > 0) {
			result = "success";
		}
		
		out.print(result);
	}
	
	
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "/view/auth/account/add";
	}
	/**
	 * 保存新帐户
	 * @param account
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Sys_account account,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (account == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		account.setId(UUID.randomUUID().toString());
		//生成默认密码md5
		HashMap<String, Object> configMap = getConfig();
		String pass = configMap.get("PASSWORD").toString();
		if (pass != null && !pass.equals("")) {
			pass = MD5.encode(pass);
		}
		else {
			pass = MD5.encode("password");
		}
		account.setPassword(pass);
		account = accountService.insert(account);
		
		if (account == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	
	/**
	 * 打开设置帐户角色页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/setrole",method=RequestMethod.GET)
	public ModelAndView setrole(String id,ModelMap modelMap) {
		//获取帐户对象
		Sys_account account = accountService.getById(id);
		//获取选中帐户的角色,供页面实现和删除
		Sys_role role = null;
		if (null != account.getRoleid() && !account.getRoleid().equals("")) {
			role = roleService.getById(account.getRoleid());
		}
		modelMap.put("role", role);
		modelMap.put("account", account);
		
		//获取角色供选择
		List<Sys_role> sys_roles = roleService.list();
		
		modelMap.put("sys_roles", sys_roles);
		
		return new ModelAndView("/view/auth/account/setrole",modelMap);
	}
	/**
	 * 保存组的角色
	 * @param orgid
	 * @param roleid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/saverole",method=RequestMethod.POST)
	public void saverole(String id,String roleid,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (id == null || id.equals("") || roleid == null || roleid.equals("")) {
			result = "failure";
			out.print(result);
			return;
		}
		
		Boolean b = accountService.saverole(id, roleid);
		
		if (!b) {
			result = "failure";
		}
		
		out.print(result);
	}
	/**
	 * 移除帐户的角色
	 * @param id
	 * @param roleid
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/removerole",method=RequestMethod.POST)
	public void removerole(String id,String roleid,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (id == null || id.equals("") || roleid == null || roleid.equals("")) {
			result = "failure";
			out.print(result);
			return;
		}
		
		Boolean b = accountService.removerole(id, roleid);
		
		if (!b) {
			result = "failure";
		}
		
		out.print(result);
	}
	
    /**
     * 设置账户物业项目操作权限
     * */
    @RequestMapping(value="/setAuthority",method=RequestMethod.GET)
    public ModelAndView setAuthority(String id,ModelMap modelMap) {
        //所有项目
        List<Project> list = projectService.list();
        String listString = JSON.toJSONString(list);
        //已设置操作权的项目
        Sys_account account = accountService.getById(id);
        String proList = account.getProjectid();
        if("".equals(proList) || proList == null)
            proList="[]";
        modelMap.put("account_id", id);
        modelMap.put("proList", proList);
        modelMap.put("projectList", listString);
        
        return new ModelAndView("/view/auth/account/setAuthority", modelMap);
    }
    /**
     * 执行账户物业项目操作权限
     * @param id      账户ID
     * @param proListString 项目节点JSON
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/tosetAuthority",method=RequestMethod.POST)
    public void toSetAuthority(String id,String proListString,HttpServletResponse response) throws IOException {
        
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        String result = "failure";
        
        Sys_account account = accountService.getById(id);
        account.setProjectid(proListString);
        int num = accountService.update(account);
        if (num > 0 ) {
            result = "success";
        }
        out.print(result);
    }

}
