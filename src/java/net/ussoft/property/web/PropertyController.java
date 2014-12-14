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
import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Standingbook;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Sys_role;
import net.ussoft.property.model.Unit;
import net.ussoft.property.model.Unitterm;
import net.ussoft.property.service.IChargeitemService;
import net.ussoft.property.service.ILeaseAgreementService;
import net.ussoft.property.service.ILeaseService;
import net.ussoft.property.service.IMeterItemService;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IBookService;
import net.ussoft.property.service.IUnitService;
import net.ussoft.property.service.IUnittermService;
import net.ussoft.property.util.MD5;
import net.ussoft.property.vo.UnitAll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 项目管理
 * @author hex
 *
 */

@Controller
@RequestMapping(value="property")
public class PropertyController extends BaseConstroller {
	
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private ILeaseService leaseService;
	
	@Resource
	private ILeaseAgreementService leaseAgreementService;
	
	@Resource
	private IMeterItemService meterItemService;
	
	@Resource
	private IChargeitemService chargeitemService;
	
	@Resource
	private IBookService standingbookService;
	
	@Resource
	private IUnittermService unittermService;
	/**
	 * 物业管理->单元管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		modelMap = super.getModelMap("PROPERTY","");
				
		List<Project> list = projectService.list();
		String listString = JSON.toJSONString(list);
		modelMap.put("projectList", listString);
		
		return new ModelAndView("/view/property/list",modelMap);
	}
	/**
	 * 列出单元信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Unit unit,ModelMap modelMap) {
		modelMap.put("projeuctid", unit.getProjeuctid());
		List<Unit> list = unitService.search(unit);
		if(list != null){
			Map<String, Unit> map = new HashMap<String,Unit>();
			for (int i = 0; i < list.size(); i++) {
				Unit u = list.get(i);
				map.put(u.getUnitrowsort() + "-" + u.getUnitsort(), u);
			}
			modelMap.put("UnitMap", JSON.toJSONString(map));
			System.out.println(JSON.toJSONString(map));
		}
		return new ModelAndView("/view/property/unit/list",modelMap);
	}
	/**
	 * 列出单元信息以及相关的所有信息
	 * @param modelMap
	 * @author 王姿勃
	 * @date:2014-11-26
	 * @return
	 */
	@RequestMapping(value = "/listAll")
	public ModelAndView listAll(Unit unit,ModelMap modelMap) {
		modelMap.put("projeuctid", unit.getProjeuctid());
//		UnitAll unitAll= unitService.searchAll(unit);
//		
//		modelMap.put("UnitAll", JSON.toJSONString(unitAll));
//		System.out.println(JSON.toJSONString(unitAll));
		return new ModelAndView("/view/property/unit/edit",modelMap);
	}
	
	/**
	 * 添加单元信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(String projeuctid,ModelMap modelMap) {
		modelMap.put("projeuctid", projeuctid);
		return new ModelAndView("/view/property/unit/add",modelMap);
	}
	
	/**
	 * 保存新帐户
	 * @param account
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Unit unit,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("测试");
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		List<Unit> list = unitService.search(unit);
		if(list!=null && list.size()>0){
			result = "db_exist";
			out.print(result);
			return;
		}
		unit.setId(UUID.randomUUID().toString());
		
		unit = unitService.insert(unit);
		
		if (unit == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/delete")
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int num = unitService.delete(ids);
		String result = "failure";
		if (num > 0 ) {
			result = "success";
		}
		
		out.print(result);
	}
	/**
	 * 打开编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(String id,ModelMap modelMap) {
		//判断id是否存在
		if (id == null || id.equals("")) {
			return null;
		}
		//获取对象
		Unit unit = unitService.getById(id);//单元信息
		
		//查询开始
		Lease lease = leaseService.getById(unit.getLeaseid());//租户资料
		Lease_agreement leaseAgreement = new Lease_agreement();
		leaseAgreement.setUnitid(unit.getId());
		List<Lease_agreement> agreementList = leaseAgreementService.list(leaseAgreement);//租赁合同
		
		
		Meteritem meteritem = new Meteritem();
		meteritem.setUnitid(unit.getId());
		List<Meteritem> meteritemList = meterItemService.list(meteritem);//抄表记录
		
		Chargeitem chargeitem = new Chargeitem();
		chargeitem.setUnitid(unit.getId());
		List<Chargeitem> chargeItemList = chargeitemService.list(chargeitem); //收费项目
		
		List<Unitterm> unittermList = unittermService.search(unit.getId());//单元账期,按照unittermcode由大到小排序
		
		Standingbook standingbook  = new Standingbook();
		standingbook.setUnitid(unit.getId());
		if(unittermList!=null &&unittermList.size()>0){
			standingbook.setUnittermid(unittermList.get(0).getId());
		}
		List<Standingbook> standingbookList = standingbookService.search(standingbook);//单元台帐,默认显示的是最近一期的台帐
		
		
		//查询结束
		
		modelMap.put("projeuctid", unit.getProjeuctid());
		modelMap.put("unit", unit);//单元信息
		modelMap.put("lease", lease);//租户资料
		modelMap.put("agreementList", agreementList);//租赁合同
		modelMap.put("meteritemList", meteritemList);//抄表记录
		modelMap.put("chargeItemList", chargeItemList);//收费项目
		modelMap.put("standingbookList", standingbookList);//单元台帐
		modelMap.put("unittermList", unittermList);//单元账期
		
		
//		UnitAll unitAll = unitService.searchAll(id);
//		modelMap.put("UnitAll", JSON.toJSONString(unitAll));
//		System.out.println(JSON.toJSONString(unitAll));
		
		return new ModelAndView("/view/property/unit/edit",modelMap);
		
		
	}
	
}
