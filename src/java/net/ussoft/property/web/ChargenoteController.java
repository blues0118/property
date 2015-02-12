package net.ussoft.property.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Chargenote;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IChargeitemService;
import net.ussoft.property.service.IChargenoteService;

@Controller
@RequestMapping(value="chargenote")
public class ChargenoteController extends BaseConstroller  {
	@Resource
	private IChargenoteService chargenoteService;
	/**
	 * 收费管理页面打开列表
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public void list(Integer page,String searchTxt,  HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Chargenote> pageBean = new PageBean<Chargenote>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("chargedate");
		
		Chargenote t = new Chargenote();
		if(searchTxt!=null && !"".equals(searchTxt)){
			t.setUnitid(searchTxt);
		}
		
		//获取数据
		pageBean = chargenoteService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
		
	}
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/chargenoteDetail",method=RequestMethod.GET)
	public ModelAndView chargenoteDetail(String id,ModelMap modelMap) {
		//获取对象
		Chargenote chargenote = new Chargenote();
		chargenote.setUnittermid(id);
		List<Chargenote> chargenoteList =  chargenoteService.list(chargenote);
		if(chargenoteList!=null && chargenoteList.size()>0){
			modelMap.put("chargenote", chargenoteList.get(0));
		}else{
			modelMap.put("chargenote", chargenote);
		}
		
		return new ModelAndView("/view/property/chargenote/chargenotedetail",modelMap);
	}
}
