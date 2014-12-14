package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;
import net.ussoft.property.service.IBookService;
import net.ussoft.property.service.IProjectService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="standingbook")
public class BookController extends BaseConstroller {
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IBookService standingBookService;
	
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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(Integer page,String searchTxt,ModelMap modelMap, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Bookterm> pageBean = new PageBean<Bookterm>();

		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("termcode");
		
		Bookterm t = new Bookterm();
		//t.setProjectid(modelMap.get("projeuctid").toString());
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
	
	/**
	 * 打开添加页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap modelMap) {
		return "/view/standingbook/add";
	}

	/**
	 * 保存新帐户
	 * @param standingbookterm
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Bookterm standingbookterm,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		if (standingbookterm == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		// id
		standingbookterm.setId(UUID.randomUUID().toString());
		// 项目id
		standingbookterm.setProjectid("sdffadfa");  //TODO
		standingbookterm = standingBookService.insert(standingbookterm);
		
		if (standingbookterm == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		out.print(result);
	}
}
