package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IUnitService;
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
		}
		return new ModelAndView("/view/property/unit/list",modelMap);
	}
	
	/**
	 * 添加单元信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(String rowNum,String projeuctid,ModelMap modelMap) {
		modelMap.put("rowNum", rowNum);
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
	public void save(Unit unit,String rowNum,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (unit == null || rowNum == null) {
			result = "failure";
			out.print(result);
			return;
		}
		
		String[] tmp = rowNum.split("-");
		
		unit.setUnitsort(Integer.parseInt(tmp[1]));
		unit.setUnitrowsort(Integer.parseInt(tmp[0]));
		unit.setId(UUID.randomUUID().toString());
		
		unit = unitService.insert(unit);
		
		if (unit == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
	
}
