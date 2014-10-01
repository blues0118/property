package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.StandingbookDao;
import net.ussoft.property.model.Standingbook;

import org.springframework.stereotype.Repository;

@Repository("standingbookDao")
public class StandingbookDaoImpl extends BaseDaoMysqlImpl<Standingbook, String> implements StandingbookDao {

	public StandingbookDaoImpl() {
		super(Standingbook.class);
	}
}
