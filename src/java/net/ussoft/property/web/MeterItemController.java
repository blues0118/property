package net.ussoft.property.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.service.IMeterItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="meterItem")
public class MeterItemController {
	@Resource
	private IMeterItemService meterItemService;
	
	/**
	 * 抄表管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Meteritem> pageBean = new PageBean<Meteritem>();
		
		//每页行数
		Integer pageSize = 10;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("createtime");
		
		Meteritem t = new Meteritem();
		if(searchTxt !=null &&!"".equals(searchTxt)){
			t.setUnitid(searchTxt);
		}
		
		//获取数据
		pageBean = meterItemService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	/**
	 * 帐户管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listMeteritem", method = RequestMethod.POST)
	public void list(String unitId,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Meteritem meteritem = new Meteritem();
		meteritem.setUnitid(unitId);
		
		List<Meteritem> t = new ArrayList<Meteritem>();
		
		
		//获取数据
		t = meterItemService.list(meteritem);
		
		
		String json = JSON.toJSONString(t);
		out.print(json);
	}
}
