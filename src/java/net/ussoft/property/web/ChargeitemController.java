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
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public void list(Integer page,String unitid,String iswatch, HttpServletResponse response) throws Exception {
		
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
				map.put("chargeperiodunit",obj.getIntValue("chargepriceunit"));
				map.put("chargeperiod",obj.getIntValue("chargeperiod"));
				map.put("chargeperiodunit",obj.getIntValue("chargeperiodunit"));
				
			}else{
				JSONObject obj = jsonArr.getJSONObject(0);
				map.put("watch_price",obj.getDoubleValue("watch_price"));
			}
			c.add(map);
		}
		String json = JSON.toJSONString(c);
		System.out.println("json=="+json);
		out.print(json);
		
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
	@RequestMapping(value="/editforunit",method=RequestMethod.GET)
	public ModelAndView editforunit(String id,ModelMap modelMap) {
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
