package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value="book")
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
	 * 单元台账列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/listBook", method = RequestMethod.POST)
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
//		List<Unitterm> unittermlist = unittermService.search(unitid);
//		if(unittermlist!=null && unittermlist.size()>0){
//			t.setUnittermid(unittermlist.get(0).getId());
//		}
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
	 * 查询收款通知单基础信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/chargeNotice", method = RequestMethod.POST)
	public void chargeNotice(String unitid,String unittermid, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//第一步：现在台账表中查询 查表收款
		Book t = new Book();
		if(unitid!=null && !"".equals(unitid)){
			t.setUnitid(unitid);
		}
		if(unittermid!=null && !"".equals(unittermid)){
			t.setUnittermid(unittermid);
		}
		
		//获取台账中的数据
		List<Map<String,Object>> bookList = bookService.list(t);
		
		//itemcode-费用名称；lastnumber-起 ；newnumber-止;newnumber-lastnumber:数量
		//chargesum/(newnumber-lastnumber)单价；chargesum-金额;滞纳金；bookmemo-备注
		
		String json = JSON.toJSONString(bookList);
		out.print(json);
	}
		/**
		 * 单元台账确认收款
		 * @param modelMap
		 * @return
		 */
		@RequestMapping(value = "/confirmReceived", method = RequestMethod.POST)
		public void confirmReceived(String id,String unittermid, HttpServletResponse response) throws Exception {
			
			response.setContentType("text/xml;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			Book t = new Book();
			if(id!=null && !"".equals(id)){
				t.setId(id);
			}
			if(unittermid!=null && !"".equals(unittermid)){
				t.setUnittermid(unittermid);
			}
			//更新数据
			Boolean flag = bookService.confirmReceived(t);
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("status", flag);
//			resultMap.put("unittermlist", unittermlist);
			String json = JSON.toJSONString(resultMap);
			out.print(json);
		}
}
