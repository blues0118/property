package net.ussoft.property.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.service.IPropertyService;

@Service
public class PropertyService implements IPropertyService {
	@Resource
	private MeteritemDao meterItemDao;
	@Resource
	private ChargeitemDao chargeitemDao;
	@Resource
	private BookDao bookDao;
	@Resource
	private AccountDao accountDao;
	
	@Override
	public List<Map<String, Object>> queryChargeitemForMap(String projectid,String unitids) {
		
		StringBuffer sql = new StringBuffer("SELECT c.*,u.unitcode FROM chargeitem c left join unit u on c.unitid = u.id WHERE 1=1 ");
		
		if(projectid!=null && !"".equals(projectid)){
			sql.append(" and c.projectid='"+projectid+"'");
		}
		if(unitids!=null && !"".equals(unitids)){
			String[] unitidList = unitids.split(",");
			if(unitidList.length==1){
				sql.append(" and c.unitid ='"+unitidList[0]+"'");
			}else{
				StringBuffer unitids_ = new StringBuffer("'"+unitidList[0]+"'");
				for(int count=1;count<unitids.length();count++){
					unitids_.append(",'"+unitidList[count]+"'");
				}
				sql.append(" and c.unitid in("+unitids_+")");
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDateStr = format.format(new Date());
		sql.append(" and c.reminddate<='"+nowDateStr+"'");
		
		sql.append(" and c.iswatch='0' and c.unitid !='SYSTEM' order by c.unitid");//查询出非按表计费的收费类型
		
		List<Object> values=new ArrayList<Object>();
		List<Map<String,Object>> chargeitemList = chargeitemDao.searchForMap(sql.toString(), values);
		
		List<Map<String, Object>> chargeitemListChg = new ArrayList<Map<String,Object>>();
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
			map.put("chargepriceunit",obj.getString("chargepriceunit"));
			map.put("chargeperiod",translateChargeItem("chargeperiod",String.valueOf(obj.getIntValue("chargeperiod"))));
			map.put("chargeperiodunit",obj.getString("chargeperiodunit"));
			map.put("watch_price",obj.getDoubleValue("watch_price"));
			chargeitemListChg.add(map);
		}
		return chargeitemListChg;
	}

	@Override
	public List<Map<String, Object>> queryBookForMap(String projectid,String unitids) {
		StringBuffer sql = new StringBuffer("select b.*,u.unitcode,ci.itemcontent from book b left join unit u on b.unitid = u.id")
				.append(" left join chargeitem ci on b.itemid = ci.id where 1=1");
		if(projectid!=null && !"".equals(projectid)){
			sql.append(" and b.projectid='"+projectid+"'");
		}
		if(unitids!=null && !"".equals(unitids)){
			String[] unitidList = unitids.split(",");
			if(unitidList.length==1){
				sql.append(" and b.unitid ='"+unitidList[0]+"'");
			}else{
				StringBuffer unitids_ = new StringBuffer("'"+unitidList[0]+"'");
				for(int count=1;count<unitids.length();count++){
					unitids_.append(",'"+unitidList[count]+"'");
				}
				sql.append(" and b.unitid in("+unitids_+")");
			}
		}
		sql.append(" and b.chargestatus='0' ");
		List<Object> values = new ArrayList<Object>();
		
		List<Map<String,Object>> bookList = bookDao.searchForMap(sql.toString(), values);
		List<Map<String, Object>> bookListChg = new ArrayList<Map<String,Object>>();
		for(int i=0;i<bookList.size();i++){
			Map<String , Object> map = new HashMap<String, Object>();
			Map<String,Object> book = bookList.get(i);
			map.put("id", book.get("id"));
			map.put("projectid", book.get("projectid"));
			map.put("unitid", book.get("unitid"));
			map.put("chargetime", book.get("chargetime"));
			map.put("chargeovertime", book.get("chargeovertime"));
			map.put("itemid", book.get("itemid"));
			map.put("chargestatus", book.get("chargestatus"));
			map.put("iswatch", book.get("iswatch"));
			map.put("noteid", book.get("noteid"));
			map.put("termid", book.get("termid"));
			map.put("unittermid", book.get("unittermid"));
			
			map.put("unitcode", book.get("unitcode"));
			map.put("itemcode", book.get("itemcode"));
			map.put("lastnumber", book.get("lastnumber"));
			map.put("newnumber", book.get("newnumber"));
			map.put("chargesum", book.get("chargesum"));
			map.put("bookmemo", book.get("bookmemo"));
			//收费内容
			JSONArray jsonArr = JSONObject.parseArray(String.valueOf(book.get("itemcontent")));
			JSONObject obj = jsonArr.getJSONObject(0);
			map.put("watch_price",obj.getDoubleValue("watch_price"));
			bookListChg.add(map);
		}
		return bookListChg;
	}
	@Override
	public List<Map<String, Object>> queryCommonForMap(String projectid,
			String unitids) {
		StringBuffer sql = new StringBuffer(" select * from sys_config where configkey = 'SYSNAME' ");
		List<Object> values = new ArrayList<Object>();
		
		return bookDao.searchForMap(sql.toString(), values);
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

}
