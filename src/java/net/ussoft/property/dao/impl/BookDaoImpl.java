package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.model.Book;

import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl extends BaseDaoMysqlImpl<Book, String> implements BookDao {

	public BookDaoImpl() {
		super(Book.class);
	}
}
