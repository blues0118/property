package net.ussoft.property.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.IMeterService;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IStandingbooktermService;
import net.ussoft.property.service.IUnittermService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="meter")
public class MeterController extends BaseConstroller  {
	
	@Resource
	private IProjectService projectService;
	@Resource
	private IMeterService meterService;
	
	/**
	 * 物业管理->抄表管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		modelMap = super.getModelMap("METER","");
		Sys_account account = super.getSessionAccount();
		List<Project> list = projectService.list();
		String listString = JSON.toJSONString(list);
		modelMap.put("projectList", listString);
		
		return new ModelAndView("/view/meter/list",modelMap);
	}
	/**
	 * 列出账期显示页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Unit unit,ModelMap modelMap) {
		modelMap.put("projeuctid", unit.getProjeuctid());
		return new ModelAndView("/view/meter/listMeter",modelMap);
	}
	/**
	 * 账期管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/meterList")
	public void meterList(Unit unit,Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Meter> pageBean = new PageBean<Meter>();
		
		//每页行数
		Integer pageSize = 10;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termcode");
		
		Meter t = new Meter();
		t.setProjectid(unit.getProjeuctid());
		
		//获取数据
		pageBean = meterService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	
	/**
	 * 抄表账期抄表记录列表。
	 * @param meterid
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/meteritem")
	public void meteritem(Unit unit,String meterid,int page, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Meteritem> pageBean = new PageBean<Meteritem>();
		
		//每页行数
		Integer pageSize = 10;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termcode");
		
		Meteritem t = new Meteritem();
		t.setProjectid(unit.getProjeuctid());
		t.setId(meterid);
		
		//获取数据
//		pageBean = meterService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	
}

