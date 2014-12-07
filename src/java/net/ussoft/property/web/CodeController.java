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
import net.ussoft.property.model.Sys_code;
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
@RequestMapping(value="code")
public class CodeController extends BaseConstroller {
	
	@Resource
	private ICodeService codeService;
	
	/**
	 * 打开系统代码管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("SYSTEM","CODE");
		
		return new ModelAndView("/view/system/code/list",modelMap);
	}
	
	
	/**
	 * 系统代码列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Sys_code> pageBean = new PageBean<Sys_code>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("sort");
		
		Sys_code t = new Sys_code();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setFieldcncode(searchTxt);	//字段中文名称
			t.setFieldcode(searchTxt);		//字段名称
		}
		
		//获取数据
		pageBean = codeService.list(t, pageBean);
		
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
		Sys_code code = codeService.getById(id);
		modelMap.put("syscode", code);
		
		return new ModelAndView("/view/system/code/add", modelMap);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView update(Sys_code code,ModelMap modelMap) throws IOException {
		
		int num = codeService.update(code);
		String result = "更新完成。";
		if (num <= 0 ) {
			result = "更新出现错误，请重新尝试，或与管理员联系。";
		}
		modelMap.put("syscode", code);
		modelMap.put("result", result);
		return new ModelAndView("/view/system/charge/add",modelMap);
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
				codeService.delete(id);
			}
		}
		result = "success";
		
		out.print(result);
	}
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap modelMap) {
		Sys_code syscode = new Sys_code();
		modelMap.put("syscode", syscode);
		return "/view/system/code/add";
	}
	/**
	 * 保存
	 * @param code
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(Sys_code code,ModelMap modelMap) throws IOException {
		if (code != null ) {
			code.setId(UUID.randomUUID().toString());
		}
		code = codeService.insert(code);
		String result = "添加完成。";
		if (code == null ) {
			result = "更新出现错误，请重新尝试，或与管理员联系。";
		}
		modelMap.put("code", code);
		modelMap.put("result", result);
		return new ModelAndView("/view/system/charge/add",modelMap);
	}
	
}
