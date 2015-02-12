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
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Chargeitemcontent;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IChargeitemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 收费项目
 * @author guodh
 *
 */

@Controller
@RequestMapping(value="charge")
public class ChargeitemController extends BaseConstroller {
	
	@Resource
	private IChargeitemService chargeitemService;
	
	/**
	 * 打开收费管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("SYSTEM","CHARGE");
		
		return new ModelAndView("/view/system/charge/list",modelMap);
	}
	
	/**
	 * 收费管理页面打开列表
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public void list(Integer page,String unitid,String iswatch, HttpServletResponse response) throws Exception {
		try{
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Chargeitem> pageBean = new PageBean<Chargeitem>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("itemsort");
		
		Chargeitem t = new Chargeitem();
		if(iswatch !=null && !"".equals(iswatch)){
			t.setIswatch(Integer.valueOf(iswatch));
		}
		if(unitid !=null && !"".equals(unitid)){
			t.setUnitid(unitid);
		}else{
			t.setUnitid("SYSTEM");
		}
		System.out.println("iswatch=="+iswatch+"==unitid=="+unitid);
		
		//获取数据
		pageBean = chargeitemService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		List<Map<String, Object>> c = new ArrayList<Map<String,Object>>();
		List<Chargeitem> ciList = pageBean.getList();
		for(int i=0;i<ciList.size();i++){
			Map<String , Object> map = new HashMap<String, Object>();
			Chargeitem chargeitem = ciList.get(i);
			map.put("id", chargeitem.getId());
			map.put("itemcode", chargeitem.getItemcode());
			map.put("chargeremark", chargeitem.getChargeremark());
			map.put("iswatch", chargeitem.getIswatch());
			map.put("watchtype", chargeitem.getWatchtype());
			map.put("itemsort", chargeitem.getItemsort());
			map.put("projectid", chargeitem.getProjectid());
			map.put("unitid", chargeitem.getUnitid());
			map.put("watchnumber", chargeitem.getWatchnumber());
			map.put("begindate", chargeitem.getBegindate());
			map.put("reminddate", chargeitem.getReminddate());
			map.put("nextdate", chargeitem.getNextdate());
			//收费内容
			JSONArray jsonArr = JSONObject.parseArray(chargeitem.getItemcontent());
			if(chargeitem.getIswatch() == 0){
				JSONObject obj = jsonArr.getJSONObject(0);
				map.put("itemtype",obj.getIntValue("itemtype"));
				map.put("itemcatagory",obj.getIntValue("itemcatagory"));
				map.put("itemmode",obj.getIntValue("itemmode"));
				map.put("itemunit",obj.getIntValue("itemunit"));
				map.put("chargecatagory",obj.getIntValue("chargecatagory"));
				map.put("chargeprice",obj.getDoubleValue("chargeprice"));
				map.put("chargeperiodunit",obj.get("chargepriceunit"));
				map.put("chargeperiod",obj.getIntValue("chargeperiod"));
				map.put("chargeperiodunit",obj.get("chargeperiodunit"));
				
			}else{
				JSONObject obj = jsonArr.getJSONObject(0);
				map.put("watch_price",obj.getDoubleValue("watch_price"));
			}
			c.add(map);
		}
		String json = JSON.toJSONString(c);
		System.out.println("json=="+json);
		out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取收费提醒的收费项目
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listForRemind")
	public void listForRemind(Integer page,String projeuctId,String unitid,String iswatch, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Chargeitem t = new Chargeitem();
		if(iswatch !=null && !"".equals(iswatch)){
			t.setIswatch(Integer.valueOf(iswatch));
		}
		if(unitid !=null && !"".equals(unitid)){
			t.setUnitid(unitid);//当为批量查询时，unitid为多个unitid组合在一起的串
		}
		if(projeuctId !=null && !"".equals(projeuctId)){
			t.setProjectid(projeuctId);
		}
		System.out.println("projeuctId="+projeuctId+"iswatch=="+iswatch+"==unitid=="+unitid);
		
		//获取数据
		List<Map<String,Object>> chargeitemList= chargeitemService.list(projeuctId,unitid,iswatch);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> c = new ArrayList<Map<String,Object>>();
		for(int i=0;i<chargeitemList.size();i++){
			Map<String , Object> map = new HashMap<String, Object>();
			Map<String,Object> chargeitem = chargeitemList.get(i);
			map.put("id", chargeitem.get("id"));
			map.put("itemcode", chargeitem.get("itemcode"));
			map.put("chargeremark", chargeitem.get("chargeremark"));
			map.put("iswatch", translateChargeItem("iswatch",String.valueOf(chargeitem.get("iswatch"))));
			map.put("watchtype", translateChargeItem("watchtype",String.valueOf(chargeitem.get("watchtype"))));
			map.put("itemsort", chargeitem.get("itemsort"));
			map.put("projectid", chargeitem.get("projectid"));
			map.put("unitid", chargeitem.get("unitid"));
			map.put("watchnumber", chargeitem.get("watchnumber"));
			map.put("begindate", chargeitem.get("begindate"));
			map.put("reminddate", chargeitem.get("reminddate"));
			map.put("nextdate", chargeitem.get("nextdate"));
			map.put("unitcode", chargeitem.get("unitcode"));
			map.put("lastnumber", chargeitem.get("lastnumber"));
			map.put("newnumber", chargeitem.get("newnumber"));
			//收费内容
			JSONArray jsonArr = JSONObject.parseArray(String.valueOf(chargeitem.get("itemcontent")));
			JSONObject obj = jsonArr.getJSONObject(0);
			map.put("itemtype",translateChargeItem("itemtype",String.valueOf(obj.getIntValue("itemtype"))));
			map.put("itemcatagory",translateChargeItem("itemcatagory",String.valueOf(obj.getIntValue("itemcatagory"))));
			map.put("itemmode",translateChargeItem("itemmode",String.valueOf(obj.getIntValue("itemmode"))));
			map.put("itemunit",translateChargeItem("itemunit",String.valueOf(obj.getIntValue("itemunit"))));
			map.put("chargecatagory",translateChargeItem("chargecatagory",String.valueOf(obj.getIntValue("chargecatagory"))));
			map.put("chargeprice",translateChargeItem("chargeprice",String.valueOf(obj.getDoubleValue("chargeprice"))));
			map.put("chargeperiodunit",translateChargeItem("chargepriceunit",String.valueOf(obj.getIntValue("chargepriceunit"))));
			map.put("chargeperiod",translateChargeItem("chargeperiod",String.valueOf(obj.getIntValue("chargeperiod"))));
			map.put("chargeperiodunit",translateChargeItem("chargeperiodunit",String.valueOf(obj.getIntValue("chargeperiodunit"))));
			map.put("watch_price",obj.getDoubleValue("watch_price"));
			c.add(map);
		}
		String json = JSON.toJSONString(c);
		System.out.println("json=="+json);
		out.print(json);
		
	}
	public String translateChargeItem(String name,String cellvalue){
		if("itemtype".equals(name)){
    		if("1".equals(cellvalue)){
    			return "收入";
    		}else if("2".equals(cellvalue)){
    			return "支出";
    		}else{
    			return "";
    		}
    	}else if("itemcatagory".equals(name)){
    		if("1".equals(cellvalue)){
    			return "正常";
    		}else if("2".equals(cellvalue)){
    			return "押金";
    		}else if("3".equals(cellvalue)){
    			return "预收款";
    		}else{
    			return "";
    		}
    	}else if("itemmode".equals(name)){
    		if("1".equals(cellvalue)){
    			return "使用面积";
    		}else if("2".equals(cellvalue)){
    			return "个数";
    		}else if("3".equals(cellvalue)){
    			return "建筑面积";
    		}
    	}else if("itemunit".equals(name)){
    		if("1".equals(cellvalue)){
    			return "按次收费";
    		}else if("2".equals(cellvalue)){
    			return "按天收费";
    		}else if("3".equals(cellvalue)){
    			return "按月收费";
    		}else if("4".equals(cellvalue)){
    			return "按年收费";
    		}else{
    			return "";
    		}
    	}else if("chargecatagory".equals(name)){
    		if("1".equals(cellvalue)){
    			return "周期性";
    		}else if("2".equals(cellvalue)){
    			return "一次性";
    		}else if("3".equals(cellvalue)){
    			return "临时性";
    		}else if("4".equals(cellvalue)){
    			return "季节性";
    		}else{
    			return "";
    		}
    	}else if("chargeprice".equals(name)){
    		if(cellvalue!=null){
    			return cellvalue;//cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
    		}else{
    			return "";
    		}
    	}else if("chargepriceunit".equals(name)){
    		if(cellvalue!=null){
    			return cellvalue;//cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
    		}else{
    			return "";
    		}
    	}else if("chargeperiodunit".equals(name)){
    		if(cellvalue!=null){
    			return cellvalue;//cellvalueJson[0].chargeprice+"/"+cellvalueJson[0].chargepriceunit;
    		}else{
    			return "";
    		}
    	}else if("chargeperiod".equals(name)){
    		if(cellvalue!=null ){
    			return cellvalue;//cellvalueJson[0].chargeperiod+"/"+cellvalueJson[0].chargeperiodunit;
    		}else{
    			return "";
    		}
    	}else if("watch_price".equals(name)){
    		if(cellvalue!=null){
    			return cellvalue;//cellvalueJson[0].watch_price;
    		}else{
    			return "";
    		}
    	}else if("iswatch".equals(name)){
    		if("0".equals(cellvalue)){
    			return "否";
    		}else if("1".equals(cellvalue)){
    			return "是";
    		}else{
    			return "";
    		}
    	}else if("watchtype".equals(name)){
    		if("0".equals(cellvalue)){
    			return "水费";
    		}else if("1".equals(cellvalue)){
    			return "电费";
    		}else if("2".equals(cellvalue)){
    			return "燃气费";
    		}else{
    			return "";
    		}
    	}
		return cellvalue;
	}
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/addforunit",method=RequestMethod.GET)
	public ModelAndView addforunit(String unitid,String iswatch,ModelMap modelMap) {
		//获取对象
		Chargeitem chargeitem = new Chargeitem();
		if(unitid!=null && !"".equals(unitid)){
			chargeitem.setUnitid(unitid);
		}
		if(iswatch!=null && !"".equals(iswatch)){
			chargeitem.setIswatch(Integer.valueOf(iswatch));
		}
		modelMap.put("chargeitem", chargeitem);
		if("0".equals(iswatch)){
			return new ModelAndView("/view/property/chargeitem/addchargeitem",modelMap);
		}
		return new ModelAndView("/view/property/chargeitem/addmeterchargeitem",modelMap);
	}
	/**
	 * 打开查看页面
	 * @return
	 */
	@RequestMapping(value="/remindChargeitem",method=RequestMethod.GET)
	public ModelAndView remindChargeitem(String unitid,String iswatch,ModelMap modelMap) {
		//获取对象
		Chargeitem chargeitem = new Chargeitem();
		if(unitid!=null && !"".equals(unitid)){
			chargeitem.setUnitid(unitid);
		}
		if(iswatch!=null && !"".equals(iswatch)){
			chargeitem.setIswatch(Integer.valueOf(iswatch));
		}
		modelMap.put("chargeitem", chargeitem);
		if("0".equals(iswatch)){
			return new ModelAndView("/view/property/chargeitem/remindChargeitem",modelMap);
		}
		return new ModelAndView("/view/property/chargeitem/remindMeterChargeitem",modelMap);
	}
	/**
	 * 打开查看页面
	 * @return
	 */
	@RequestMapping(value="/chargenotePrintPre",method=RequestMethod.GET)
	public ModelAndView chargenotePrintPre(String unitid,String projectid,ModelMap modelMap) {
		modelMap.put("unitid", unitid);
		modelMap.put("projectid", projectid);
		return new ModelAndView("/view/property/chargeitem/remindBatch",modelMap);
	}
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/addforchargenote",method=RequestMethod.GET)
	public ModelAndView addforchargenote(String unitid,String iswatch,ModelMap modelMap) {
		//获取对象
		Chargeitem chargeitem = new Chargeitem();
		if(unitid!=null && !"".equals(unitid)){
			chargeitem.setUnitid(unitid);
		}
		if(iswatch!=null && !"".equals(iswatch)){
			chargeitem.setIswatch(Integer.valueOf(iswatch));
		}
		modelMap.put("chargeitem", chargeitem);
		return new ModelAndView("/view/property/chargeitem/addchargeitemforchargenote",modelMap);
	}
	/**
	 * 打开批量添加收费项目页面
	 * @return
	 */
	@RequestMapping(value="/addbatchitemforunit",method=RequestMethod.GET)
	public ModelAndView addBatchChargeitemForUnit(String ids,String iswatch,ModelMap modelMap) {
		//获取对象
		Chargeitem chargeitem = new Chargeitem();
		chargeitem.setUnitid("");
		modelMap.put("chargeitem", chargeitem);
		modelMap.put("ids", ids);
		return new ModelAndView("/view/property/chargeitem/batchadd",modelMap);
	}
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(ModelMap modelMap) {
		//获取对象
		Chargeitem chargeitem = new Chargeitem();
		modelMap.put("chargeitem", chargeitem);
		return new ModelAndView("/view/system/charge/add",modelMap);
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(Chargeitem chargeitem,ModelMap modelMap) {
		
		if (chargeitem != null ) {
			chargeitem.setId(UUID.randomUUID().toString());
			chargeitem.setUnitid("SYSTEM");
			chargeitem.setProjectid("SYSTEM");
		}
		
		chargeitem = chargeitemService.insert(chargeitem);
		String result = "添加完成。";
		if (chargeitem == null ) {
			result = "更新出现错误，请重新尝试，或与管理员联系。";
		}
		modelMap.put("chargeitem", chargeitem);
		modelMap.put("result", result);
		return new ModelAndView("/view/system/charge/add",modelMap);
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
		
		int num = chargeitemService.delete(ids);
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
		Chargeitem chargeitem = chargeitemService.getById(id);
		
		//收费内容
		JSONArray jsonArr = JSONObject.parseArray(chargeitem.getItemcontent());
		Chargeitemcontent itemcontent = new Chargeitemcontent();
		if(chargeitem.getIswatch() == 0){
			JSONObject obj = jsonArr.getJSONObject(0);
			itemcontent.setItemtype(obj.getIntValue("itemtype"));
			itemcontent.setItemcatagory(obj.getIntValue("itemcatagory"));
			itemcontent.setItemmode(obj.getIntValue("itemmode"));
			itemcontent.setItemunit(obj.getIntValue("itemunit"));
			itemcontent.setChargecatagory(obj.getIntValue("chargecatagory"));
			itemcontent.setChargeprice(obj.getDoubleValue("chargeprice"));
			itemcontent.setChargeperiodunit(obj.getIntValue("chargepriceunit"));
			itemcontent.setChargeperiod(obj.getIntValue("chargeperiod"));
			itemcontent.setChargeperiodunit(obj.getIntValue("chargeperiodunit"));
			
		}else{
			JSONObject obj = jsonArr.getJSONObject(0);
			itemcontent.setWatch_price(obj.getDoubleValue("watch_price"));
		}
		modelMap.put("chargeitem", chargeitem);
		modelMap.put("itemcontent", itemcontent);
		return new ModelAndView("/view/system/charge/add",modelMap);
	}
	/**
	 * 打开单元的收费项目编辑页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/editforunit")
	public ModelAndView editforunit(String id,ModelMap modelMap) {
		try{
			//判断id是否存在
			if (id == null || id.equals("")) {
				return null;
			}
			//获取对象
			Chargeitem chargeitem = chargeitemService.getById(id);
			
			//收费内容
			JSONArray jsonArr = JSONObject.parseArray(chargeitem.getItemcontent());
			//Chargeitemcontent itemcontent = new Chargeitemcontent();
			Map<String,Object> itemcontent = new HashMap<String,Object>();
			if(chargeitem.getIswatch() == 0){
				JSONObject obj = jsonArr.getJSONObject(0);
				itemcontent.put("itemtype", obj.get("itemtype"));
				itemcontent.put("itemcatagory", obj.get("itemcatagory"));
				itemcontent.put("itemmode", obj.get("itemmode"));
				itemcontent.put("itemunit", obj.get("itemunit"));
				itemcontent.put("chargecatagory", obj.get("chargecatagory"));
				itemcontent.put("chargeprice", obj.get("chargeprice"));
				itemcontent.put("chargepriceunit", obj.get("chargepriceunit"));
				itemcontent.put("chargeperiod", obj.get("chargeperiod"));
				itemcontent.put("chargeperiodunit", obj.get("chargeperiodunit"));
				
			}else{
				JSONObject obj = jsonArr.getJSONObject(0);
				itemcontent.put("watch_price", obj.get("watch_price"));
				
			}
			modelMap.put("chargeitem", chargeitem);
			modelMap.put("itemcontent", itemcontent);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("/view/property/chargeitem/edit",modelMap);
	}
	
	/**
	 * 执行更新
	 * @param id
	 * @param value
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView update(Chargeitem chargeitem,ModelMap modelMap) {
		
		int num = chargeitemService.update(chargeitem);
		String result = "更新完成。";
		if (num <= 0 ) {
			result = "更新出现错误，请重新尝试，或与管理员联系。";
		}
		modelMap.put("chargeitem", chargeitem);
		modelMap.put("result", result);
		return new ModelAndView("/view/system/charge/add",modelMap);
	}
	/**
	 * 执行更新
	 * @param id
	 * @param value
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/savechargeitem",method=RequestMethod.POST)
	public void savechargeitem(String unitid,String ids,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int num = chargeitemService.addChargeitem(unitid,ids);
		String result = "success";
		if (num <= 0 ) {
			result = "failure";
		}
		out.print(result);
	}
	
}
