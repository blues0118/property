package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.model.Meter;

import org.springframework.stereotype.Repository;

@Repository("meterDao")
public class MeterDaoImpl extends BaseDaoMysqlImpl<Meter, String> implements MeterDao {

	public MeterDaoImpl() {
		super(Meter.class);
	}
}
