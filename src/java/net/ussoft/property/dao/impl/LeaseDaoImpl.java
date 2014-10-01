package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.LeaseDao;
import net.ussoft.property.model.Lease;

import org.springframework.stereotype.Repository;

@Repository("leaseDao")
public class LeaseDaoImpl extends BaseDaoMysqlImpl<Lease, String> implements LeaseDao {

	public LeaseDaoImpl() {
		super(Lease.class);
	}
}
