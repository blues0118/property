package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Meteritem;

import org.springframework.stereotype.Repository;

@Repository("meteritemDao")
public class MeteritemDaoImpl extends BaseDaoMysqlImpl<Meteritem, String> implements MeteritemDao {

	public MeteritemDaoImpl() {
		super(Meteritem.class);
	}
}
