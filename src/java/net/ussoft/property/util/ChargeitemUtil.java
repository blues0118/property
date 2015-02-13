package net.ussoft.property.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ChargeitemUtil {
	private final static Logger log = new Logger(CommonUtils.class);
    private static final String DATE_STYLE = "yyyy-MM-dd";
    private static final String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";
    public static Map<String,Object> translateItemcontent(String itemcontent) throws Exception {
    	Map<String,Object> result = new HashMap<String,Object>();
    	if(itemcontent==null || "".equals(itemcontent)){
    		return null;
    	}
    	JSONArray jsonArr = JSONObject.parseArray(itemcontent);
		JSONObject obj = jsonArr.getJSONObject(0);
    	if("1".equals(obj.get("itemtype"))){
    		result.put("itemtype", "收入");
    	}else if("2".equals(obj.get("itemtype"))){
    		result.put("itemtype", "支出");
    	}else{
    		result.put("itemtype", "");
    	}
    	if("1".equals(obj.get("itemcatagory"))){
    		result.put("itemcatagory", "正常");
    	}else if("2".equals(obj.get("itemcatagory"))){
    		result.put("itemcatagory", "押金");
    	}else if("3".equals(obj.get("itemcatagory"))){
    		result.put("itemcatagory", "预收款");
    	}else{
    		result.put("itemcatagory", "");
    	}
    	if("1".equals(obj.get("itemmode"))){
    		result.put("itemmode", "使用面积");
    	}else if("2".equals(obj.get("itemmode"))){
    		result.put("itemmode", "个数");
    	}else if("3".equals(obj.get("itemmode"))){
    		result.put("itemmode", "建筑面积");
    	}else{
    		result.put("itemmode", "");
    	}
    	if("1".equals(obj.get("itemunit"))){
    		result.put("itemunit", "按次收费");
    	}else if("2".equals(obj.get("itemunit"))){
    		result.put("itemunit", "按天收费");
    	}else if("3".equals(obj.get("itemmode"))){
    		result.put("itemunit", "按月收费");
    	}else if("4".equals(obj.get("itemmode"))){
    		result.put("itemunit", "按年收费");
    	}else{
    		result.put("itemunit", "");
    	}
    	if("1".equals(obj.get("chargecatagory"))){
    		result.put("chargecatagory", "周期性");
    	}else if("2".equals(obj.get("chargecatagory"))){
    		result.put("chargecatagory", "一次性");
    	}else if("3".equals(obj.get("chargecatagory"))){
    		result.put("chargecatagory", "临时性");
    	}else if("4".equals(obj.get("chargecatagory"))){
    		result.put("chargecatagory", "季节性");
    	}else{
    		result.put("chargecatagory", "");
    	}
    	if(obj.get("chargeprice")!=null && !"".equals(obj.get("chargeprice"))){
    		result.put("chargeprice", obj.get("chargeprice"));
    	}else{
    		result.put("chargeprice", "");
    	}
    	if("1".equals(obj.get("chargepriceunit"))){
    		result.put("chargepriceunit", "日");
    	}else if("2".equals(obj.get("chargepriceunit"))){
    		result.put("chargepriceunit", "月");
    	}else if("3".equals(obj.get("chargepriceunit"))){
    		result.put("chargepriceunit", "年");
    	}else{
    		result.put("chargepriceunit", "");
    	}
    	if("1".equals(obj.get("chargeperiodunit"))){
    		result.put("chargeperiodunit", "日");
    	}else if("2".equals(obj.get("chargeperiodunit"))){
    		result.put("chargeperiodunit", "月");
    	}else if("3".equals(obj.get("chargeperiodunit"))){
    		result.put("chargeperiodunit", "年");
    	}else{
    		result.put("chargeperiodunit", "");
    	}
    	if(obj.get("chargeperiod")!=null && !"".equals(obj.get("chargeperiod"))){
    		result.put("chargeperiod", obj.get("chargeperiod"));
    	}else{
    		result.put("chargeperiod", "");
    	}
    	if(obj.get("watch_price")!=null && !"".equals(obj.get("watch_price"))){
    		result.put("watch_price", obj.get("watch_price"));
    	}else{
    		result.put("watch_price", "");
    	}
    	if("0".equals(obj.get("iswatch"))){
    		result.put("iswatch", "否");
    	}else if("1".equals(obj.get("iswatch"))){
    		result.put("iswatch", "是");
    	}else{
    		result.put("iswatch", "");
    	}
    	if("0".equals(obj.get("watchtype"))){
    		result.put("watchtype", "水费");
    	}else if("1".equals(obj.get("watchtype"))){
    		result.put("watchtype", "电费");
    	}else if("2".equals(obj.get("watchtype"))){
    		result.put("watchtype", "燃气费");
    	}else{
    		result.put("watchtype", "");
    	}
    	
    	return result;
    }
}
