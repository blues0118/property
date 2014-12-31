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
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unitterm;
import net.ussoft.property.service.IBookService;
import net.ussoft.property.service.IBooktermService;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IUnittermService;
import net.ussoft.property.util.CommonUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="bookterm")
public class BookController extends BaseConstroller {
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IBooktermService booktermService;
	@Resource
	private IUnittermService unittermService;
	
	@Resource
	private IBookService bookService;
	
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("BOOKTERM","index");
		
		//物业项目树形数据取得
		List<Project> list = projectService.list();
		String listString = JSON.toJSONString(list);
		modelMap.put("projectList", listString);
		
		return new ModelAndView("/view/bookterm/index",modelMap);
	}
	
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/detaillist", method = RequestMethod.POST)
	public void detaillist(Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Unitterm> pageBean = new PageBean<Unitterm>();

		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("projectid");
		pageBean.setOrderType("desc");
		
		Unitterm t = new Unitterm();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setTermid(searchTxt);
		}
		
		//获取数据
		//pageBean = booktermService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(String projectid, Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Bookterm> pageBean = new PageBean<Bookterm>();

		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("createtime");
		pageBean.setOrderType("desc");
		
		Bookterm t = new Bookterm();
		t.setProjectid(projectid);
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setTermcode(searchTxt);
		}
		
		//获取数据
		pageBean = booktermService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	/**
	 * 单元台账列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/listStandingbook", method = RequestMethod.POST)
	public void listStandingbook(Integer page,String unitid,String unittermid, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Book> pageBean = new PageBean<Book>();

		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("itemcode");
		pageBean.setOrderType("desc");
		
		Book t = new Book();
		if(unitid!=null && !"".equals(unitid)){
			t.setUnitid(unitid);
		}
		List<Unitterm> unittermlist = unittermService.search(unitid);
		if(unittermlist!=null && unittermlist.size()>0){
			t.setUnittermid(unittermlist.get(0).getId());
		}
		if(unittermid!=null && !"".equals(unittermid)){
			t.setUnittermid(unittermid);
		}
		
		//获取数据
		pageBean = bookService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
//		resultMap.put("unittermlist", unittermlist);
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/relist",method=RequestMethod.GET)
	public ModelAndView relist(String projectid,ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/bookterm/list",modelMap);
	}
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(String projectid, ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/bookterm/add",modelMap);
	}
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/addChargeitemforunit",method=RequestMethod.GET)
	public ModelAndView addChargeitemforunit(String termid,String unitid, ModelMap modelMap) {
		modelMap.put("termid", termid);
		modelMap.put("unitid", unitid);
		return new ModelAndView("/view/property/chargeitem/addchargeitemforunit",modelMap);
	}

	/**
	 * 保存总账期
	 * @param bookterm
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Bookterm bookterm,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (bookterm == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		// 插入总台账账期

		// id
		bookterm.setId(UUID.randomUUID().toString());
		// session取得
		Sys_account accountSession = super.getSessionAccount();
		// 创建时间
		bookterm.setCreatetime(CommonUtils.getTimeStamp());
		// 创建人
		bookterm.setCreateman(accountSession.getAccountname());
		// 账期状态
		bookterm.setTremstatus(0);
		// 总帐期
		bookterm = booktermService.insertBookTerm(bookterm, accountSession);
		if (bookterm == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	/**
	 * 删除总账期。
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
			// 总台账账期删除
			booktermService.delete(id);
			// 单元台账账期删除
			//unitTermService.deleteByTermid(id);
			// 台账删除 TODO 删除否？
			// 收款单删除 TODO 删除否？
			// 抄表管理删除
			//meterService.deleteByTermid(id);
			// 抄表内容删除   TODO 删除否？
		}
		result = "success";
		
		out.print(result);
	}
	/**
	 * 删除单元台帐。
	 * @param orgid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteunitbook",method=RequestMethod.POST)
	public void deleteunitbook(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 单元台帐删除
			bookService.delete(id);
			
		}
		result = "success";
		
		out.print(result);
	}
	/**
	 * 打开总账期编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(String id,ModelMap modelMap) {
		Bookterm bookterm = booktermService.getById(id);
		modelMap.put("bookterm", bookterm);
		
		return new ModelAndView("/view/bookterm/edit", modelMap);
	}
	/**
	 * 打开单元台帐编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editforunit",method=RequestMethod.GET)
	public ModelAndView editforunit(String id,ModelMap modelMap) {
		Book book = bookService.getById(id);
		modelMap.put("book", book);
		
		return new ModelAndView("/view/property/book/edit", modelMap);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(Bookterm standingbookterm,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (standingbookterm == null ) {
			out.print(result);
			return;
		}
		int num = booktermService.update(standingbookterm);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	/**
	 * 执行更新
	 * @param id
	 * @param value
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/savechargeitem",method=RequestMethod.POST)
	public void savechargeitem(String projeuctid,String unitid,String termid,String unittermid,String ids,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int num = bookService.addChargeitem(projeuctid,unitid,termid,unittermid,ids);
		String result = "success";
		if (num <= 0 ) {
			result = "failure";
		}
		out.print(result);
	}
}
