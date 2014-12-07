package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.LeaseAgreementDao;
import net.ussoft.property.dao.ProjectDao;
import net.ussoft.property.model.Lease_agreement;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.ILeaseAgreementService;

@Service
public class LeaseAgreementService implements ILeaseAgreementService {
	
	@Resource
	private LeaseAgreementDao leaseAgreementDao;
	
	@Override
	public int delete(String id) {
		
		return leaseAgreementDao.del(id);
	}

	@Override
	public Lease_agreement getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lease_agreement getByWhere(Lease_agreement leaseAgreement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lease_agreement insert(Lease_agreement leaseAgreement) {
		return leaseAgreementDao.save(leaseAgreement);
	}

	@Override
	public List<Lease_agreement> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Lease_agreement> list(Lease_agreement t,
			PageBean<Lease_agreement> pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lease_agreement> list(Lease_agreement leaseAgreement) {
		return leaseAgreementDao.search(leaseAgreement);
	}

	@Override
	public List<Lease_agreement> list(String agreementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Lease_agreement leaseAgreement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Lease_agreement> search(Lease_agreement t) {
		return leaseAgreementDao.search(t);
	}

}
