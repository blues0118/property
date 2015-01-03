package net.ussoft.property.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Chargeitem;
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
	@Resource
	private ChargeitemDao chargeitemDao;
	
	@Resource
	private BookDao bookDao;
	
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
	@Override
	public PageBean<Book> list(Book t, PageBean<Book> pageBean) {
		return bookDao.search(t, pageBean);
	}
	@Override
	public int addChargeitem(String projectid,String unitid, String termid,String unittermid, String ids) {
		try{
			String[] addStrings = ids.split(",");
			Chargeitem chargeitem = null;
			Book book;
			for (String id : addStrings) {
				book = new Book();
				chargeitem = new Chargeitem();
				chargeitem.setId(id);
				chargeitem = chargeitemDao.search(chargeitem).get(0);
				book.setId(UUID.randomUUID().toString());
				book.setProjectid(projectid);
				book.setUnitid(unitid);
				book.setTermid(unittermid);
				book.setUnittermid(unittermid);
				bookDao.save(book);
			}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
	@Override
	public int delete(String id) {
		return bookDao.del(id);
	}
	@Override
	public Book getById(String id) {
		return bookDao.get(id);
	}
	@Override
	public Book insert(Book book) {
		bookDao.save(book);
		return book;
	}
}
