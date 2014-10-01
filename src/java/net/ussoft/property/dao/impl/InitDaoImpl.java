package net.ussoft.property.dao.impl;

import org.springframework.stereotype.Repository;

import net.ussoft.property.dao.InitDao;
import net.ussoft.property.model.Sys_init;

@Repository("initDao")
public class InitDaoImpl extends BaseDaoMysqlImpl<Sys_init, String> implements InitDao {

	public InitDaoImpl() {
		super(Sys_init.class);
	}
}
