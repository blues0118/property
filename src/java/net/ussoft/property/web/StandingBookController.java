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
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.model.Standingbookterm;
import net.ussoft.property.service.IProjectService;
import net.ussoft.property.service.IStandingBookService;

@Controller
@RequestMapping(value="standingbook")
public class StandingBookController extends BaseConstroller {
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IStandingBookService standingBookService;
	
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		modelMap = super.getModelMap("STANDINGBOOK","index");//TODO ??
		
		//物业项目树形数据取得
		List<Project> list = projectService.list();
		String listString = JSON.toJSONString(list);
		modelMap.put("projectList", listString);
		
		return new ModelAndView("/view/standingbook/index",modelMap);
		//return new ModelAndView("/view/standingbook/list",modelMap);
	}
	
	/**
	 * 台账管理初期
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Standingbookterm> pageBean = new PageBean<Standingbookterm>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termcode");
		
		Standingbookterm t = new Standingbookterm();
		t.setProjectid("sdffadfa");
		if (null != searchTxt && !"".equals(searchTxt)) {
			t.setProjectid(searchTxt);
			t.setTermmemo(searchTxt);
		}
		
		//获取数据
		pageBean = standingBookService.list(t, pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
/*		//物业项目树形数据取得
		List<Standingbookterm> list = standingBookService.list();
		//String listString = JSON.toJSONString(list);
		modelMap.put("standingBookList", list);
		
		return new ModelAndView("/view/standingbook/booklist",modelMap);*/
	}
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/relist",method=RequestMethod.GET)
	public ModelAndView relist(String projeuctid,ModelMap modelMap) {
		modelMap.put("projeuctid", projeuctid);
		return new ModelAndView("/view/standingbook/list",modelMap);
	}
}
