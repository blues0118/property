package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.ConfigDao;
import net.ussoft.property.model.Sys_config;

import org.springframework.stereotype.Repository;

@Repository("configDao")
public class ConfigDaoImpl extends BaseDaoMysqlImpl<Sys_config, String> implements ConfigDao {

	public ConfigDaoImpl() {
		super(Sys_config.class);
	}
}
