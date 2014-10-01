package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Project;
import net.ussoft.property.service.IProjectService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 项目管理
 * @author wangf
 *
 */

@Controller
@RequestMapping(value="project")
public class ProjectController extends BaseConstroller {
	
	
	@Resource
	private IProjectService projectService;
	
	/**
	 * 打开角色管理
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap) {
		
		List<Project> list = projectService.list();
		
		String listString = JSON.toJSONString(list);
		
		modelMap.put("projectList", listString);
		return new ModelAndView("/view/system/project/list",modelMap);
	}
	
	/**
	 * 保存新项目
	 * @param project
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Project project,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "";
		if (project == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		project.setId(UUID.randomUUID().toString());
		
		
		project = projectService.insert(project);
		
		if (project == null ) {
			result = "failure";
			out.print(result);
			return;
		}
		
		project = projectService.getById(project.getId());
		//生成项目的treenode。结构例如 1#2#3，用来标示层级关系
		Project parentProject = projectService.getById(project.getParentid());
		
		String treenode = parentProject.getTreenode() + "#" + project.getNodeindex();
		
		//更新treenode
		Project tmpProject = new Project();
		tmpProject.setId(project.getId());
		tmpProject.setTreenode(treenode);
		projectService.update(tmpProject);
//		values.clear();
//		values.add(treenode);
//		values.add(project.getId());
//		projectDao.update("update project set treenode=? where id=?", values);
		
		result = project.getId();
		out.print(result);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(Project project,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "failure";
		if (project == null ) {
			out.print(result);
			return;
		}
		int num = projectService.update(project);
		if (num > 0 ) {
			result = "success";
		}
		out.print(result);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/delete")
	public void delete(String id,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int num = projectService.delete(id);
		String result = "failure";
		if (num > 0 ) {
			result = "success";
		}
		
		out.print(result);
	}
}
