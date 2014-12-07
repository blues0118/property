package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.ILeaseService;
import net.ussoft.property.service.IUnitService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="lease")
public class LeaseController {
	
	
	@Resource
	private ILeaseService leaseService;
	
	@Resource
	private IUnitService unitService;
	
	/**
	 * 添加业主信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(String unitid , ModelMap modelMap) {
		modelMap.put("unitid", unitid);
		return new ModelAndView("/view/property/lease/add",modelMap);
	}
	/**
	 * 保存业主信息
	 * @param lease
	 * @param unitid
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Lease lease,String unitid,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("测试");
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String[] unitidSplit = unitid.split(",");
		String result = "success";
		for(int count=0;count<unitidSplit.length;count++){
			Unit unit = unitService.getById(unitidSplit[count]);
			
			lease.setId(UUID.randomUUID().toString());
			lease = leaseService.insert(lease);
			//更新单元信息表的unitid
			unit.setLeaseid(lease.getId());
			unitService.update(unit);
			if (lease == null ) {
				result = "failure";
				out.print(result);
				return;
			}
		}
		
		out.print(result);
	}
}
