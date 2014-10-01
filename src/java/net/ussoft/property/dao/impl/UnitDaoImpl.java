package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.UnitDao;
import net.ussoft.property.model.Unit;

import org.springframework.stereotype.Repository;

@Repository("unitDao")
public class UnitDaoImpl extends BaseDaoMysqlImpl<Unit, String> implements UnitDao {

	public UnitDaoImpl() {
		super(Unit.class);
	}
}
