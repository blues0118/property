package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.StandingbooktermDao;
import net.ussoft.property.model.Standingbookterm;

import org.springframework.stereotype.Repository;

@Repository("standingbooktermDao")
public class StandingbooktermDaoImpl extends BaseDaoMysqlImpl<Standingbookterm, String> implements StandingbooktermDao {

	public StandingbooktermDaoImpl() {
		super(Standingbookterm.class);
	}
}
