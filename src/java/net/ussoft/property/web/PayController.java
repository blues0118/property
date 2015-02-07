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
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Equipment;
import net.ussoft.property.model.Equipmentcontent;
import net.ussoft.property.model.Otherpay;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Staff;
import net.ussoft.property.model.Staffcontent;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.service.IBooktermService;
import net.ussoft.property.service.IPayService;
import net.ussoft.property.service.IProjectService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="pay")
public class PayController extends BaseConstroller {
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IPayService payService;
	
	@Resource
	private IBooktermService booktermService;
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("PAY","PAY");
		// session取得
		Sys_account accountSession = super.getSessionAccount();
		
		//物业项目树形数据取得
		List<Project> list = projectService.list(accountSession.getId());
		String listString = JSON.toJSONString(list);
		modelMap.put("projectList", listString);
		
		return new ModelAndView("/view/pay/index",modelMap);
	}
	
	/**
	 * 打开添加管理页面
	 * @return
	 */
	@RequestMapping(value="/addlist",method=RequestMethod.GET)
	public ModelAndView addlist(String projectid,ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/pay/addlist",modelMap);
	}
	
	/**
	 * 打开添加设备页面
	 * @return
	 */
	@RequestMapping(value="/addequip",method=RequestMethod.GET)
	public ModelAndView addequip(String projectid, ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/pay/addequip",modelMap);
	}

	/**
	 * 保存设备信息
	 * @param equipment
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/saveequip",method=RequestMethod.POST)
	public void saveequip(Equipment equipment,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (equipment == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		// 设备管理
		// id
		equipment.setId(UUID.randomUUID().toString());
		equipment = payService.insertEquipment(equipment);
		if (equipment == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	/**
	 * 设备管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/equiplist", method = RequestMethod.POST)
	public void equiplist(String projectid, Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Equipment> pageBean = new PageBean<Equipment>();

		//每页行数
		Integer pageSize = 17;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("createtime");
		pageBean.setOrderType("desc");
		
		Equipment t = new Equipment();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setCode(searchTxt);
		}
		
		//获取数据
		pageBean = payService.getByProjetId(projectid, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开设备编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editEquip",method=RequestMethod.GET)
	public ModelAndView editEquip(String id,ModelMap modelMap) {
		Equipment equipment = payService.getEquipById(id);
		modelMap.put("equipment", equipment);
		
		return new ModelAndView("/view/pay/editEquip", modelMap);
	}
	
	@RequestMapping(value="/updateEquip",method=RequestMethod.POST)
	public void updateEquip(Equipment equipment,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (equipment == null ) {
			out.print(result);
			return;
		}
		int num = payService.updateEquip(equipment);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除设备。
	 * @param orgid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteEquip",method=RequestMethod.POST)
	public void deleteEquip(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 设备删除
			payService.deleteEquip(id);
			// 设备维修删除 TODO
		}
		result = "success";
		
		out.print(result);
	}
	
	/**
	 * 打开添加员工页面
	 * @return
	 */
	@RequestMapping(value="/addStaff",method=RequestMethod.GET)
	public ModelAndView addStaff(String projectid, ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/pay/addstaff",modelMap);
	}
	
	/**
	 * 打开添加设备支出页面
	 * @return
	 */
	@RequestMapping(value="/addEquipPay",method=RequestMethod.GET)
	public ModelAndView addEquipPay(String id, ModelMap modelMap) {
		modelMap.put("equipmentid", id);
		
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		return new ModelAndView("/view/pay/addequippay",modelMap);
	}
	
	/**
	 * 打开添加员工支出页面
	 * @return
	 */
	@RequestMapping(value="/addStaffPay",method=RequestMethod.GET)
	public ModelAndView addStaffPay(String staffId, ModelMap modelMap) {
		modelMap.put("staffId", staffId);
		
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		return new ModelAndView("/view/pay/addstaffpay",modelMap);
	}

	/**
	 * 保存员工信息
	 * @param staff
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/savestaff",method=RequestMethod.POST)
	public void savestaff(Staff staff,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (staff == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		// 员工管理
		// id
		staff.setId(UUID.randomUUID().toString());
		staff = payService.insertStaff(staff);
		if (staff == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	/**
	 * 员工管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/staffList", method = RequestMethod.POST)
	public void staffList(String projectid, Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Staff> pageBean = new PageBean<Staff>();

		//每页行数
		Integer pageSize = 17;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("staffcode");
		pageBean.setOrderType("desc");
		
		Staff t = new Staff();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setStaffcode(searchTxt);
		}
		
		//获取数据
		pageBean = payService.getStaffList(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开员工编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editStaff",method=RequestMethod.GET)
	public ModelAndView editStaff(String id,ModelMap modelMap) {
		Staff staff = payService.getStaffById(id);
		modelMap.put("staff", staff);
		
		return new ModelAndView("/view/pay/editStaff", modelMap);
	}
	
	@RequestMapping(value="/updateStaff",method=RequestMethod.POST)
	public void updateStaff(Staff staff,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (staff == null ) {
			out.print(result);
			return;
		}
		int num = payService.updateStaff(staff);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除设备。
	 * @param orgid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteStaff",method=RequestMethod.POST)
	public void deleteStaff(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 员工删除
			payService.deleteStaff(id);
			// 员工共组删除 TODO
		}
		result = "success";
		
		out.print(result);
	}
	
	/**
	 * 打开添加其他支出页面
	 * @return
	 */
	@RequestMapping(value="/addOther",method=RequestMethod.GET)
	public ModelAndView addOther(ModelMap modelMap) {
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		return new ModelAndView("/view/pay/addother",modelMap);
	}

	/**
	 * 保存员工信息
	 * @param otherpay
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/saveOther",method=RequestMethod.POST)
	public void saveOther(Otherpay otherpay,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (otherpay == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		// 其他支出
		// id
		otherpay.setId(UUID.randomUUID().toString());
		otherpay = payService.insertOther(otherpay);
		if (otherpay == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	/**
	 * 其他支出初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/otherList", method = RequestMethod.POST)
	public void otherList(Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Otherpay> pageBean = new PageBean<Otherpay>();

		//每页行数
		Integer pageSize = 17;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("paydate");
		pageBean.setOrderType("desc");
		
		Otherpay t = new Otherpay();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setPaydate(searchTxt);;
		}
		
		//获取数据
		pageBean = payService.getOtherList(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开其他支出编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editOther",method=RequestMethod.GET)
	public ModelAndView editOther(String id,ModelMap modelMap) {
		Otherpay otherpay = payService.getOtherById(id);
		modelMap.put("otherpay", otherpay);
		
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		return new ModelAndView("/view/pay/editOther", modelMap);
	}
	
	@RequestMapping(value="/updateOther",method=RequestMethod.POST)
	public void updateOther(Otherpay otherpay,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (otherpay == null ) {
			out.print(result);
			return;
		}
		int num = payService.updateOther(otherpay);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除设备。
	 * @param orgid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteOther",method=RequestMethod.POST)
	public void deleteOther(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 员工删除
			payService.deleteOther(id);
		}
		result = "success";
		
		out.print(result);
	}

	/**
	 * 保存设备支出信息
	 * @param equipmentcontent
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/saveequipPay",method=RequestMethod.POST)
	public void saveequipPay(Equipmentcontent equipmentcontent,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (equipmentcontent == null ) {
			result = "failure";
			out.print(result);
			return;
		}

		// id
		equipmentcontent.setId(UUID.randomUUID().toString());
		equipmentcontent = payService.insertEquipmentcontent(equipmentcontent);
		if (equipmentcontent == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}

	/**
	 * 保存员工支出信息
	 * @param staffcontent
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/savestaffPay",method=RequestMethod.POST)
	public void savestaffPay(Staffcontent staffcontent,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (staffcontent == null ) {
			result = "failure";
			out.print(result);
			return;
		}

		// id
		staffcontent.setId(UUID.randomUUID().toString());
		staffcontent = payService.insertStaffcontent(staffcontent);
		if (staffcontent == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
	/**
	 * 设备支出管理
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/equipPayList", method = RequestMethod.POST)
	public void equipPayList(String id, Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Equipmentcontent> pageBean = new PageBean<Equipmentcontent>();

		//每页行数
		Integer pageSize = 17;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termid");
		pageBean.setOrderType("desc");
		
		//获取数据
		pageBean = payService.getByEquipId(id, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 员工工资管理
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/staffContentList", method = RequestMethod.POST)
	public void staffContentList(String staffid, Integer page,String searchTxt, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Staffcontent> pageBean = new PageBean<Staffcontent>();

		//每页行数
		Integer pageSize = 17;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termid");
		pageBean.setOrderType("desc");
		
		Equipment t = new Equipment();
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setCode(searchTxt);
		}
		
		//获取数据
		pageBean = payService.getByStaffId(staffid, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	
	/**
	 * 打开设备支出编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editEquipPay",method=RequestMethod.GET)
	public ModelAndView editEquipPay(String id,ModelMap modelMap) {
		Equipmentcontent equipmentcontent = payService.getEquipmentcontentById(id);
		modelMap.put("equipmentcontent", equipmentcontent);
		
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		
		return new ModelAndView("/view/pay/editEquippay", modelMap);
	}
	
	@RequestMapping(value="/updateEquippay",method=RequestMethod.POST)
	public void updateEquippay(Equipmentcontent equipmentcontent,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (equipmentcontent == null ) {
			out.print(result);
			return;
		}
		int num = payService.updateEquipmentcontent(equipmentcontent);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除员工工资。
	 * @param ids
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteEquipPay",method=RequestMethod.POST)
	public void deleteEquipPay(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 设备支出删除
			payService.deleteEquipmentcontent(id);
		}
		result = "success";
		
		out.print(result);
	}
	
	/**
	 * 打开员工工资编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editStaffcontent",method=RequestMethod.GET)
	public ModelAndView editStaffcontent(String id,ModelMap modelMap) {
		Staffcontent staffcontent = payService.getStaffcontentById(id);
		modelMap.put("staffcontent", staffcontent);
		
		List<Bookterm> termList = booktermService.getTermList();
		modelMap.put("termList", termList);
		
		return new ModelAndView("/view/pay/editStaffcontent", modelMap);
	}
	
	@RequestMapping(value="/updateStaffcontent",method=RequestMethod.POST)
	public void updateStaffcontent(Staffcontent staffcontent,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (staffcontent == null ) {
			out.print(result);
			return;
		}
		int num = payService.updateStaffcontent(staffcontent);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除员工工资。
	 * @param ids
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteStaffcontent",method=RequestMethod.POST)
	public void deleteStaffcontent(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
			// 员工删除
			payService.deleteStaffcontent(id);
		}
		result = "success";
		
		out.print(result);
	}
	
}
