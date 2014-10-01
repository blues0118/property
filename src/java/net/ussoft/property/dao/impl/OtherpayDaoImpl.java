package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.OtherpayDao;
import net.ussoft.property.model.Otherpay;

import org.springframework.stereotype.Repository;

@Repository("otherpayDao")
public class OtherpayDaoImpl extends BaseDaoMysqlImpl<Otherpay, String> implements OtherpayDao {

	public OtherpayDaoImpl() {
		super(Otherpay.class);
	}
}
