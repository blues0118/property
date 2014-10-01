package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.UnittermDao;
import net.ussoft.property.model.Unitterm;

import org.springframework.stereotype.Repository;

@Repository("unittermDao")
public class UnittermDaoImpl extends BaseDaoMysqlImpl<Unitterm, String> implements UnittermDao {

	public UnittermDaoImpl() {
		super(Unitterm.class);
	}
}
