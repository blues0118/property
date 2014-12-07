package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Lease;

public interface ILeaseService {
	/**
	 * 查看或修改选中的租户信息
	 * @param unit
	 * @return
	 */
	public Lease getById(String id);
	public List<Lease> search(Lease t);
	public Lease insert(Lease lease);
	public Lease insertBatchLease(Lease lease , String[] unitidSplit);
}
