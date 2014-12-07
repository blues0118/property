package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Unit;

/**
 * 合同管理service
 * @author wangf
 *
 */
public interface ILeaseAgreementService {
	/**
	 * 根据id，获取对象
	 * @param id
	 * @return
	 */
	public Lease_agreement getById(String id);
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Lease_agreement> list();
	
	/**
	 * 分页读取信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Lease_agreement> list(Lease_agreement t,PageBean<Lease_agreement> pageBean);
	
	/**
	 * 根据条件，获取全部信息
	 * @return
	 */
	public List<Lease_agreement> list(Lease_agreement leaseAgreement);
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	public int update(Lease_agreement leaseAgreement);
	
	/**
	 * 插入新记录
	 * @param docserver
	 * @return 
	 */
	public Lease_agreement insert(Lease_agreement leaseAgreement);
	
	/**
	 * 根据条件获取数据
	 * @param role
	 * @return
	 */
	public Lease_agreement getByWhere(Lease_agreement leaseAgreement);
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 获取用户访问的物业项目
	 * @param account_id
	 * @return
	 */
	public List<Lease_agreement> list(String agreement_id);
	/**
	 * 获取合同
	 * @param account_id
	 * @return
	 */
	public List<Lease_agreement> search(Lease_agreement t);
}
