package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.model.Bookterm;

import org.springframework.stereotype.Repository;

@Repository("booktermDao")
public class BooktermDaoImpl extends BaseDaoMysqlImpl<Bookterm, String> implements BooktermDao {

	public BooktermDaoImpl() {
		super(Bookterm.class);
	}
}
