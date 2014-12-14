package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IBookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServce implements IBookService {
	@Resource
	private BookDao standingbookDao;
	
	@Resource
	private BooktermDao standingBookTermDao;
	
	@Override
	public List<Book> search(Book t) {
		return standingbookDao.search(t);
	}
	@Override
	public List<Bookterm> list() {
		return standingBookTermDao.getAll();
	}
	
	@Override
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean) {
		pageBean = standingBookTermDao.search(t, pageBean);
		return pageBean;
	}
	
	@Transactional("txManager")
	@Override
	public Bookterm insert(Bookterm standingbookterm) {
		standingBookTermDao.save(standingbookterm);
		return standingbookterm;
	}
}
