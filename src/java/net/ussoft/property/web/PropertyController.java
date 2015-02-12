package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.Project;
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
import net.ussoft.property.service.IPropertyService;
import net.ussoft.property.service.IUnitService;
import net.ussoft.property.service.IUnittermService;
import net.ussoft.property.util.CommonUtils;
import net.ussoft.property.util.Constants;
import net.ussoft.property.util.DateUtil;
import net.ussoft.property.util.MD5;

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
	
	@Resource
	private IPropertyService propertyService;
	/**
	 * 物业管理->单元管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		modelMap = super.getModelMap("PROPERTY","");
		Sys_account account = super.getSessionAccount();
		List<Project> list = projectService.list(account.getId());
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
		List<Map<String,Object>> list = unitService.searchForMap(unit);
		if(list != null){
			Map<String, Object> map = new HashMap<String,Object>();
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> u = list.get(i);
				map.put(u.get("unitrowsort") + "-" + u.get("unitsort"), u);
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
			unitService.update(list.get(0));//如果存在就自动更新单元信息
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
		
		
//		Meteritem meteritem = new Meteritem();
//		meteritem.setUnitid(unit.getId());
//		List<Meteritem> meteritemList = meterItemService.list(meteritem);//抄表记录
		
//		Chargeitem chargeitem = new Chargeitem();
//		chargeitem.setUnitid(unit.getId());
//		List<Chargeitem> chargeItemList = chargeitemService.list(chargeitem); //收费项目
		
//		List<Unitterm> unittermList = unittermService.search(unit.getId());//单元账期,按照unittermcode由大到小排序
//		
//		Standingbook standingbook  = new Standingbook();
//		standingbook.setUnitid(unit.getId());
//		if(unittermList!=null &&unittermList.size()>0){
//			standingbook.setUnittermid(unittermList.get(0).getId());
//		}
//		List<Standingbook> standingbookList = standingbookService.search(standingbook);//单元台帐,默认显示的是最近一期的台帐
		
		
		//查询结束
		
		modelMap.put("projeuctid", unit.getProjeuctid());
		modelMap.put("unit", unit);//单元信息
		modelMap.put("lease", lease);//租户资料
		modelMap.put("agreementList", agreementList);//租赁合同
//		modelMap.put("meteritemList", meteritemList);//抄表记录
//		modelMap.put("chargeItemList", chargeItemList);//收费项目
//		modelMap.put("standingbookList", standingbookList);//单元台帐
//		modelMap.put("unittermList", unittermList);//单元账期
		
		
//		UnitAll unitAll = unitService.searchAll(id);
//		modelMap.put("UnitAll", JSON.toJSONString(unitAll));
//		System.out.println(JSON.toJSONString(unitAll));
		
		return new ModelAndView("/view/property/unit/edit",modelMap);
		
		
	}
	/**
	 * 获取批量“收款单打印”数据。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChargenote", method = RequestMethod.POST)
	public void queryChargenote(String projectid,String unitids,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Meteritem meteritem = new Meteritem();
		meteritem.setUnitid(unitids);
		
		
		//用来保存所有的收款单打印数据
		Map<String,List<Map<String,Object>>> list = new HashMap<String,List<Map<String,Object>>>(); 
		//用来存放所有的unitid值，返回到页面后用来解析list
		Map<String,Object> list_unitids = null ;
		List<Map<String,Object>> list_unitidsCopy = new ArrayList<Map<String,Object>>();
		
		//第一步：获取固定收费数据
		List<Map<String,Object>>  chargeitemList = propertyService.queryChargeitemForMap(projectid, unitids);
		//第二步：获取抄表收费数据
		List<Map<String,Object>>  bookList = propertyService.queryBookForMap(projectid, unitids);
		//第三步：获取公共数据（物业公司名称、单元编号、金额合计大小写、实收金额、制表人、打印日期、经办人）
		List<Map<String,Object>>  commonList = propertyService.queryCommonForMap(projectid, unitids);
		Object object = CommonUtils.getSessionAttribute(request, Constants.user_in_session);
        Sys_account account = object == null ? null : (Sys_account) object;  
        if(account!=null){
	        list_unitids = new HashMap<String,Object>();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        list_unitids.put("accountname", account.getAccountname());//制表人、经办人
	        list_unitids.put("printdate", format.format(new Date()));//打印日期
	        if(commonList!=null && commonList.size()>0){
	        	list_unitids.put("propertyname", commonList.get(0).get("configvalue"));//物业公司名称
	        }
	        list_unitidsCopy.add(list_unitids);
        }
		//固定收费数据解析
		Iterator iter_chargeitem = chargeitemList.iterator();
		Map<String,Object> map = null;
		Map<String,Object> mapCopy = null;
		List<Map<String,Object>> mapListCopy = null;
		int count=0;
		double amount =0.0;
		while(iter_chargeitem.hasNext()){
			map = (Map<String,Object>)iter_chargeitem.next();
			mapCopy = new HashMap<String,Object>();
			//用单元的id来分类单元与单元的收款单打印数据
			if(list.containsKey(map.get("unitid"))){//如果包含了单元的id
				mapCopy.put("unitcode", map.get("unitcode"));//单元代码
				mapCopy.put("name", map.get("itemcode"));//收费项目名称
				mapCopy.put("start", map.get("begindate"));//计费起
				mapCopy.put("end", map.get("nextdate"));//计费止
				String begindate=String.valueOf(map.get("begindate"));
				String enddate = String.valueOf(map.get("nextdate"));
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("begindate="+begindate+"=enddate="+enddate);
				if(begindate!=null && !"".equals(begindate)&& enddate!=null && !"".equals(enddate)){
					if("日".equals(map.get("chargepriceunit"))||"1".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.daysBetween(sdf.parse(begindate),sdf.parse(enddate)));//数量
					}else if("月".equals(map.get("chargepriceunit"))||"2".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.monthsBetween(sdf.parse(begindate),sdf.parse(enddate)));//数量
					}else if("年".equals(map.get("chargepriceunit"))||"3".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.yearsBetween(sdf.parse(begindate), sdf.parse(enddate)));//数量
					}
				}else{
					mapCopy.put("number","0");//数量
				}
				String chargepriceunit_="";
				if(map.get("chargepriceunit")!=null && "1".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "日";
				}else if(map.get("chargepriceunit")!=null && "2".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "月";
				}else if(map.get("chargepriceunit")!=null && "3".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "年";
				}
				mapCopy.put("unit", map.get("chargeprice")+"/"+chargepriceunit_);//单价，例：500/月
				double chargeprice_ = 0.0;
				double number_ = 0;
				if(map.get("chargeprice")!=null && !"".equals(map.get("chargeprice"))){
					chargeprice_ = Double.valueOf(String.valueOf(map.get("chargeprice")));
				}
				if(map.get("number")!=null && !"".equals(map.get("number"))){
					number_ = Double.valueOf(String.valueOf(map.get("number")));
				}
				mapCopy.put("bz", map.get("chargeremark"));//备注
				list.get(map.get("unitid")).add(mapCopy);
			}else{
				list_unitids = new HashMap<String,Object>() ;
				mapListCopy = new ArrayList<Map<String,Object>>();
				mapCopy.put("unitcode", map.get("unitcode"));//单元代码
				mapCopy.put("name", map.get("itemcode"));//收费项目名称
				mapCopy.put("start", map.get("begindate"));//计费起
				mapCopy.put("end", map.get("nextdate"));//计费止
				String begindate=String.valueOf(map.get("begindate"));
				String enddate = String.valueOf(map.get("nextdate"));
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("begindate="+begindate+"=enddate="+enddate);
				if(begindate!=null && !"".equals(begindate)&& enddate!=null && !"".equals(enddate)){
					if("日".equals(map.get("chargepriceunit"))||"1".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.daysBetween(sdf.parse(begindate),sdf.parse(enddate)));//数量
					}else if("月".equals(map.get("chargepriceunit"))||"2".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.monthsBetween(sdf.parse(begindate),sdf.parse(enddate)));//数量
					}else if("年".equals(map.get("chargepriceunit"))||"3".equals(map.get("chargepriceunit"))){
						mapCopy.put("number",DateUtil.yearsBetween(sdf.parse(begindate), sdf.parse(enddate)));//数量
					}
				}else{
					mapCopy.put("number","0");//数量
				}
				String chargepriceunit_="";
				if(map.get("chargepriceunit")!=null && "1".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "日";
				}else if(map.get("chargepriceunit")!=null && "2".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "月";
				}else if(map.get("chargepriceunit")!=null && "3".equals(map.get("chargepriceunit"))){
					chargepriceunit_ = "年";
				}
				mapCopy.put("unit", map.get("chargeprice")+"/"+chargepriceunit_);//单价，例：500/月
				double chargeprice_ = 0.0;
				double number_ = 0;
				if(map.get("chargeprice")!=null && !"".equals(map.get("chargeprice"))){
					chargeprice_ = Double.valueOf(String.valueOf(map.get("chargeprice")));
				}
				if(map.get("number")!=null && !"".equals(map.get("number"))){
					number_ = Double.valueOf(String.valueOf(map.get("number")));
				}
				mapCopy.put("amount", chargeprice_*number_);//金额
				mapCopy.put("bz", map.get("chargeremark"));//备注
				mapListCopy.add(mapCopy);
				list.put(String.valueOf(map.get("unitid")), mapListCopy);
				list_unitids.put(String.valueOf(count), map.get("unitid"));
				list_unitidsCopy.add(list_unitids);
				count++;
			}
		}
		//抄表收费数据解析
		Iterator iter = bookList.iterator();
		while(iter.hasNext()){
			map = (Map<String,Object>)iter.next();
			mapCopy = new HashMap<String,Object>();
			//用单元的id来分类单元与单元的收款单打印数据
			
			if(list.containsKey(map.get("unitid"))){//如果包含了单元的id
				mapCopy.put("unitcode", map.get("unitcode"));//单元代码
				mapCopy.put("name", map.get("itemcode"));//收费项目名称
				mapCopy.put("start", map.get("lastnumber"));//计费起
				mapCopy.put("end", map.get("newnumber"));//计费止
				double number=0.0;
				if(map.get("lastnumber")!=null && !"".equals(map.get("lastnumber"))
						&&map.get("newnumber")!=null && !"".equals(map.get("newnumber"))){
					number = Double.valueOf(String.valueOf(map.get("newnumber")))-Double.valueOf(String.valueOf(map.get("lastnumber")));
				}
				mapCopy.put("number", number);//数量
				mapCopy.put("unit", map.get("watch_price"));//单价
				mapCopy.put("amount", map.get("chargesum"));//金额
				mapCopy.put("bz", map.get("bookmemo"));//备注
				list.get(map.get("unitid")).add(mapCopy);
			}else{
				list_unitids = new HashMap<String,Object>() ;
				mapListCopy = new ArrayList<Map<String,Object>>();
				mapCopy.put("unitcode", map.get("unitcode"));//单元代码
				mapCopy.put("name", map.get("itemcode"));//收费项目名称
				mapCopy.put("start", map.get("lastnumber"));//计费起
				mapCopy.put("end", map.get("newnumber"));//计费止
				double number=0.0;
				if(map.get("lastnumber")!=null && !"".equals(map.get("lastnumber"))
						&&map.get("newnumber")!=null && !"".equals(map.get("newnumber"))){
					number = Double.valueOf(String.valueOf(map.get("newnumber")))-Double.valueOf(String.valueOf(map.get("lastnumber")));
				}
				mapCopy.put("number", number);//数量 
				mapCopy.put("unit", map.get("watch_price"));//单价
				mapCopy.put("amount", map.get("chargesum"));//金额
				mapCopy.put("bz", map.get("bookmemo"));//备注
				mapListCopy.add(mapCopy);
				list.put(String.valueOf(map.get("unitid")), mapListCopy);
				list_unitids.put(String.valueOf(count), map.get("unitid"));
				list_unitidsCopy.add(list_unitids);
				count++;
			}
		}
		//list_unitidsCopy.add(list_unitids);
		list.put("info", list_unitidsCopy);
		
		String json = JSON.toJSONString(list);
		System.out.println(json);
		out.print(json);
	}
	
}
