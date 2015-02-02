package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ussoft.property.dao.LeaseDao;
import net.ussoft.property.dao.UnitDao;
import net.ussoft.property.model.Lease;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.ILeaseService;

@Service
public class LeaseService implements ILeaseService {
	@Resource
	private LeaseDao leaseDao;
	
	@Override
	public Lease getById(String id) {
		return leaseDao.get(id);
	}
	@Override
	public List<Lease> search(Lease t) {
		return leaseDao.search(t);
	}
	@Override
	public Lease insert(Lease lease) {
		return leaseDao.save(lease);
	}
	@Transactional("txManager")
	@Override
	public Lease insertBatchLease(Lease lease , String[] unitidSplit) {
		return leaseDao.save(lease);
	}
}
