package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.WatchchangeDao;
import net.ussoft.property.model.Watchchange;

import org.springframework.stereotype.Repository;

@Repository("watchchangeDao")
public class WatchchangeDaoImpl extends BaseDaoMysqlImpl<Watchchange, String> implements WatchchangeDao {

	public WatchchangeDaoImpl() {
		super(Watchchange.class);
	}
}
