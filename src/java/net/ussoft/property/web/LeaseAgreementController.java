package net.ussoft.property.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ussoft.property.base.BaseConstroller;
import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.ILeaseAgreementService;
import net.ussoft.property.service.ILeaseService;
import net.ussoft.property.service.IProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value="agreement")
public class LeaseAgreementController extends BaseConstroller {

	
	@Resource
	private ILeaseAgreementService leaseAgreementService;
	
	@Resource
	private ILeaseService leaseService;
	
	
	private static final int BUFFER_SIZE = 2 * 1024;
	private Process process = null;
	@Autowired
    CommonsMultipartResolver multipartResolver;
	
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
	 * 上传合同信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/upload")
	public ModelAndView upload(String id, ModelMap modelMap) {
		modelMap.put("id", id);
		return new ModelAndView("/view/property/agreement/upload",modelMap);
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
		String unitid = request.getParameter("unitid");
		String leaseid = request.getParameter("leaseid");
		Lease lease = new Lease();
		lease.setId(leaseid);
		List<Lease> leaseList = leaseService.search(lease);
		Lease lease1 = leaseList.get(0);
		String result = "success";
		leaseAgreement.setId(UUID.randomUUID().toString());
		leaseAgreement.setLeasename(lease1.getLeasename());
		leaseAgreement.setLeasetype(lease1.getLeasetype());
		leaseAgreement.setLeasenumber(lease1.getLeasenumber());
		leaseAgreement.setPhonenumber(lease1.getPhonenumber());
		leaseAgreement.setMobilephone(lease1.getMobilephone());
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
	/**
	 * 帐户管理列表。
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public void list(Integer page,String searchTxt,HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		PageBean<Lease_agreement> pageBean = new PageBean<Lease_agreement>();
		
		//每页行数
		Integer pageSize = 50;
		
		pageBean.setIsPage(true);
		pageBean.setPageSize(pageSize);
		pageBean.setPageNo(page);
		
		pageBean.setOrderBy("leasecode");
		
		Lease_agreement t = new Lease_agreement();
		if (null != searchTxt && !"".equals(searchTxt)) {
			//待定
			t.setUnitid(searchTxt);
		}
		
		//获取数据
		pageBean = leaseAgreementService.list(t,pageBean);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalpages", pageBean.getPageCount());
		resultMap.put("currpage", pageBean.getPageNo());
		resultMap.put("totalrecords", pageBean.getRowCount());
		resultMap.put("rows", pageBean.getList());
		
		String json = JSON.toJSONString(resultMap);
		out.print(json);
	}
	@RequestMapping("/saveupload")
	public void saveupload(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		
    	CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;
			//获取参数
			String unitid = request.getParameter("unitid");
			String leaseid = request.getParameter("leaseid");
			Lease_agreement agreement = new Lease_agreement();
			agreement.setUnitid(unitid);
			agreement.setLeaseid(leaseid);
			//获取plupload参数
			Integer chunks = Integer.valueOf(request.getParameter("chunks"));
			String name = request.getParameter("name");
			Integer chunk = Integer.valueOf(request.getParameter("chunk"));
			//获取文件列表
			Iterator<String>  iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//获取文件对象
				MultipartFile file = multiRequest.getFile((String)iter.next());
				Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
				String year = String.valueOf(c.get(Calendar.YEAR)); 
				//获取临时文件的绝对路径
				String contextPath = getProjectRealPath() + "upload" +File.separator + "agreement" + File.separator + year + File.separator;
				//生成临时文件
				String fileIdGenerate = UUID.randomUUID().toString();
				String dstPath =  contextPath + fileIdGenerate + name.substring(name.lastIndexOf("."));
				File dstFile = new File(dstPath);
				if(!dstFile.exists()){
					dstFile.mkdirs();
				}
				// 文件已存在（上传了同名的文件）
				if (chunk == 0 && dstFile.exists()) {
					dstFile.delete();
					dstFile = new File(dstPath);
				}
				//合并文件
				cat(file, dstFile);

				//将文件路径保存到数据表中
				List<Lease_agreement> agreementList = leaseAgreementService.search(agreement);
		    	Lease_agreement leaseAgreement = agreementList.get(0);
		    	leaseAgreement.setLeasefile(contextPath);
		    	leaseAgreement.setLeasefilename(name);
		    	leaseAgreementService.update(leaseAgreement);
				// 完成一整个文件;
			}
		}
	}
	/**
     * 将原文件，拼接到目标文件dst
     * @param file
     * @param dst
     */
    private void cat(MultipartFile file, File dst) {
        InputStream in = null;
        OutputStream out = null;
        try {
            if (dst.exists()) {
                out = new BufferedOutputStream(new FileOutputStream(dst, true),BUFFER_SIZE);
            } else {
                out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
            }
            in = new BufferedInputStream(file.getInputStream(), BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
