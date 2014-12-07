package net.ussoft.property.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.ILeaseAgreementService;
import net.ussoft.property.service.IProjectService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="agreement")
public class LeaseAgreementController {

	
	@Resource
	private ILeaseAgreementService leaseAgreementService;
	
	/**
	 * 添加合同信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(String unitid ,String leaseid, ModelMap modelMap) {
		modelMap.put("unitId", unitid);
		modelMap.put("leaseid", leaseid);
		return new ModelAndView("/view/property/agreement/add",modelMap);
	}
	/**
	 * 保存合同
	 * @param account
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(Lease_agreement leaseAgreement,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("测试");
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String result = "success";
		leaseAgreement.setId(UUID.randomUUID().toString());
		
		leaseAgreement = leaseAgreementService.insert(leaseAgreement);
		
		if (leaseAgreement == null ) {
			result = "failure";
			out.print(result);
			return;
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
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int num = leaseAgreementService.delete(ids);
		String result = "failure";
		if (num > 0 ) {
			result = "success";
		}
		
		out.print(result);
	}
	
}
