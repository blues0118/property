package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.LeaseAgreementDao;
import net.ussoft.property.model.Lease_agreement;

import org.springframework.stereotype.Repository;

@Repository("leaseAgreementDao")
public class LeaseAgreementDaoImpl extends BaseDaoMysqlImpl<Lease_agreement, String> implements LeaseAgreementDao {

	public LeaseAgreementDaoImpl() {
		super(Lease_agreement.class);
	}
}
