package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.model.Chargeitem;

import org.springframework.stereotype.Repository;

@Repository("chargeitemDao")
public class ChargeitemDaoImpl extends BaseDaoMysqlImpl<Chargeitem, String> implements ChargeitemDao {

	public ChargeitemDaoImpl() {
		super(Chargeitem.class);
	}
}
