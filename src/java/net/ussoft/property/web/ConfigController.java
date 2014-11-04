package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Sys_config;
import net.ussoft.property.service.ICodeService;
import net.ussoft.property.service.IConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 参数设置
 * @author wangf
 *
 */

@Controller
@RequestMapping(value="config")
public class ConfigController extends BaseConstroller {
	
	
	@Resource
	private IConfigService configService;
	@Resource
	private ICodeService codeService;
	
	
	/**
	 * 打开系统配置页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("SYSTEM","CONFIG");
		
		return new ModelAndView("/view/system/config/list",modelMap);
	}
	
	/**
	 * 系统维护页面打开列表
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public void list(HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//获取config数据
		List<Sys_config> configList = configService.list();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", 1);
		resultMap.put("currpage", 1);
		resultMap.put("totalrecords", configList.size());
		resultMap.put("rows", configList);
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
		
	}
	
	/**
	 * 系统维护页面打开系统配置列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/list2",method=RequestMethod.GET)
	public ModelAndView list2(ModelMap modelMap) {
		modelMap = super.getModelMap("SYSTEM","CONFIG");
		
		//获取config数据
		List<Sys_config> configList = configService.list();
		modelMap.put("configList", configList);
		
		return new ModelAndView("/view/system/config/list",modelMap);
	}
	
	/**
	 * 打开系统配置编辑页面
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
		Sys_config config = configService.getById(id);
		modelMap.put("config", config);
		return new ModelAndView("/view/system/config/edit",modelMap);
	}
	
	/**
	 * 执行更新系统配置
	 * @param id
	 * @param value
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView update(Sys_config config,ModelMap modelMap) {
		//获取对象
		String value = config.getConfigvalue();
		config = configService.getById(config.getId());
		config.setConfigvalue(value);
		
		int num = configService.update(config);
		String result = "更新完成。";
		if (num <= 0 ) {
			result = "更新出现错误，请重新尝试，或与管理员联系。";
		}
		modelMap.put("config", config);
		modelMap.put("result", result);
		return new ModelAndView("/view/system/config/edit",modelMap);
	}
	
	@RequestMapping(value="/updateSetShow",method=RequestMethod.POST)
	public void updateSetShow(Sys_config config,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (config == null ) {
			out.print(result);
			return;
		}
		int num = configService.update(config);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
}
