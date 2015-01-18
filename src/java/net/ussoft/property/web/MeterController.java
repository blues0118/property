package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.IBookService;
import net.ussoft.property.service.IChargeitemService;
import net.ussoft.property.service.IMeterItemService;
import net.ussoft.property.service.IMeterService;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IUnitService;
import net.ussoft.property.util.CommonUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="meter")
public class MeterController extends BaseConstroller  {
	
	@Resource
	private IProjectService projectService;
	@Resource
	private IMeterService meterService;
	@Resource
	private IMeterItemService meteritemService;
	@Resource
	private IUnitService unitService;
	@Resource
	private IChargeitemService chargeitemService;
	@Resource
	private IBookService bookService;
	
	
	/**
	 * 物业管理->抄表管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		modelMap = super.getModelMap("METER","");
		Sys_account account = super.getSessionAccount();
		List<Project> list = projectService.list(account.getId());
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
	public ModelAndView list(String projectid,ModelMap modelMap) {
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/meter/listMeter",modelMap);
	}
	/**
	 * 账期管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/meterList",method = RequestMethod.POST)
	public void meterList(String projectid,Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Meter> pageBean = new PageBean<Meter>();
		
		//每页行数
		Integer pageSize = 10;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("meterdate");
		
		Meter t = new Meter();
		t.setProjectid(projectid);
		
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
	 * 抄表账期抄表记录列表
	 * @param meterid
	 * @param projectid
	 * @param meterstatus
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/meteritem")
	public ModelAndView meteritem(String meterid,String projectid,int meterstatus,ModelMap modelMap) {
		
		Meteritem t = new Meteritem();
		t.setProjectid(projectid);
		t.setMeterid(meterid);
		
		//获取数据
		List<Meteritem> mtList = meteritemService.list(t);
		if(mtList.size() == 0){
			//创建抄表记录
			insertMeterItem(projectid, meterid);
		}
		
//		['序号','单元编号','抄表日期','上期表数','本期表数','上期表数','本期表数','上期表数','本期表数']
		//表头
		List<String> colNames = new ArrayList<String>();
		colNames.add("单元编号");
		colNames.add("抄表日期");
		//groupHeaders
		List<Map<String, Object>> groupHeaders = new ArrayList<Map<String, Object>>();
		//colModel
		List<String> colModel = new ArrayList<String>();
		List<Meteritem> mtunitList = meteritemService.unitGroupList(meterid, projectid);
		if(mtunitList.size() > 0){
			
			colModel.add("{name:'unitcode',index:'unitcode',width:80,sortable:false,align:'center'}");
			colModel.add("{name:'date',index:'date',width:80,sortable:false,align:'center'}");
			
			Meteritem mitem = mtunitList.get(0);
			List<Meteritem> uCList = meteritemService.unitChargeList(meterid, projectid, mitem.getUnitid());
			for(int i=0;i<uCList.size();i++){
				Meteritem m = uCList.get(i);
				//groupHeaders [{startColumnName: 'a1', numberOfColumns: 2, titleText: '<em>水表1</em>'}]
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("startColumnName", "lastnum_c"+i);
				itemMap.put("numberOfColumns", 2);
				itemMap.put("titleText", "<em>" + m.getWatchcode() + "</em>");
				colNames.add("收费项ID");
				colNames.add("上期表数");
				colNames.add("本期表数");
				groupHeaders.add(itemMap);
				
				colModel.add("{name:'meteritemid" + i + "',index:'meteritemid" + i + "',width:80,align:'center'}");
				colModel.add("{name:'lastnum_c" + i + "',index:'lastnum_c" + i + "',width:80,align:'center'}");
				if(meterstatus != 1)
					colModel.add("{name:'newnum_c" + i + "',index:'newnum_c" + i + "',width:80,editrules:{number:true},sortable:false,align:'center',editable:true}");
				else
					colModel.add("{name:'newnum_c" + i + "',index:'newnum_c" + i + "',width:80,editrules:{number:true},sortable:false,align:'center',editable:false}");
				
			}
		}
		modelMap.put("meterid", meterid);
		modelMap.put("projectid", projectid);
		modelMap.put("groupHeaders", JSON.toJSONString(groupHeaders));
		modelMap.put("colModel", colModel.toString());
		modelMap.put("colNames", JSON.toJSONString(colNames));
		modelMap.put("meterstatus", meterstatus);
		return new ModelAndView("/view/meter/listMeterItem",modelMap);
	}
	
	/**
	 * 抄表账期抄表记录列表。
	 * @param meterid
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/meteritemList")
	public void meteritemList(String projectid,String meterid,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Meteritem> mtunitList = meteritemService.unitGroupList(meterid, projectid);
		for(Meteritem mitem:mtunitList){
			List<Meteritem> uCList = meteritemService.unitChargeList(meterid, projectid, mitem.getUnitid());
			Map<String, Object> map = new HashMap<String, Object>();
			//map.put("id", mitem.getUnitid()); //单元ID
			Unit unit = unitService.getById(mitem.getUnitid());
			map.put("unitcode", unit.getUnitcode());
			map.put("date", mitem.getCreatetime());
			
			for(int i=0;i<uCList.size();i++){
				Meteritem m = uCList.get(i);
				map.put("meteritemid"+i, m.getId());
				map.put("lastnum_c"+i, m.getLastnumber());
				map.put("newnum_c"+i, m.getNewnumber());
			}
			list.add(map);
		}
		
		String json = JSON.toJSONString(list);
		out.print(json);
	}
	
	/**
	 * 初始抄表账期记录
	 * */
	private void insertMeterItem(String projectid,String meterid){
//		System.out.println("没有数据");
		Unit unit = new Unit();
		unit.setProjeuctid(projectid);
		//根据项目ID查询其下的所有单元
		List<Unit> unitList = unitService.search(unit);
		for(Unit ut:unitList){
			//查询单元收费项（按表计费项）
			Chargeitem c = new Chargeitem();
			c.setProjectid(projectid);
			c.setUnitid(ut.getId());
			c.setIswatch(1); // 按表计费项
			List<Chargeitem> citemList = chargeitemService.list(c);
			for(Chargeitem ci:citemList){
				Meteritem meteritem = new Meteritem();
				meteritem.setId(UUID.randomUUID().toString());
				meteritem.setMeterid(meterid);
				meteritem.setProjectid(projectid);
				meteritem.setUnitid(ut.getId());
				meteritem.setChargeitemid(ci.getId());
				meteritem.setWatchcode(ci.getItemcode());
				meteritem.setCreatetime(CommonUtils.getTimeStamp());
				meteritem.setLastnumber(ci.getWatchnumber());
				meteritem.setNewnumber(0);
				
				meteritemService.insert(meteritem);
			}
		}
	}
	
	/**
	 * 执行更新
	 * @param id
	 * @param value
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(String unitid,String meteritemid,int newnumber,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
	
		Meteritem meteritem = new Meteritem();
		meteritem.setId(meteritemid);
		meteritem.setNewnumber(newnumber);
		int num = meteritemService.update(meteritem);
		String result = "error";
		if (num >= 0 ) {
			result = "success";
		}
		out.write(result);
	}
	
	/**
	 * 计入台账
	 * @param projectid
	 * @param meterid
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public void book(String projectid,String meterid,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Meteritem meteritem = new Meteritem();
		meteritem.setProjectid(projectid);
		meteritem.setMeterid(meterid);
		List<Meteritem> meteritemList = meteritemService.list(meteritem);
		for(Meteritem m:meteritemList){
			Book book = new Book();
			book.setId(UUID.randomUUID().toString());
			book.setProjectid(m.getProjectid());
			book.setUnitid(m.getUnitid());
//			book.setChargetime();		//计费时间
//			book.setChargeovertime();	//收费时间
			book.setItemid(m.getChargeitemid());
			book.setItemcode(m.getWatchcode());
			book.setIswatch(1);		//是否按表计费；1：按表计费
			book.setLastnumber(m.getLastnumber());
			book.setNewnumber(m.getNewnumber());
			book.setChargestatus(0);	//未结束
			Chargeitem chargeitem = chargeitemService.getById(m.getChargeitemid());
			JSONArray jsonArr = JSONObject.parseArray(chargeitem.getItemcontent());
			JSONObject obj = jsonArr.getJSONObject(0);
//			System.out.println(obj.getString("watch_price"));
			
			double sum = Double.valueOf(obj.getString("watch_price"));
			double chargesum = (m.getNewnumber() - m.getLastnumber()) * sum;
			book.setChargesum(chargesum);	//收费金额
//			book.setTermid(termid);	//总台账账期id
//			book.setUnittermid(unittermid);	//单元台账账期id
			
			bookService.insert(book);
			
			//修改单元收费项
			chargeitem.setWatchnumber(m.getNewnumber()); //最后表读数
			chargeitemService.update(chargeitem);
		}
	
		//修改抄表账期状态-计入台账为已完成
		Meter m = new Meter();
		m.setId(meterid);
		m.setMeterstatus(1);
		meterService.update(m);
		
		out.print("success");
	}
	
}

