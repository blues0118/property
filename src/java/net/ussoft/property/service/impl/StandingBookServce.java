package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;




import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbook;
import net.ussoft.property.model.Standingbookterm;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.service.IStandingBookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StandingBookServce implements IStandingBookService {
	@Resource
	private BookDao standingbookDao;
	
	@Resource
	private BooktermDao standingBookTermDao;
	
	@Override
	public List<Standingbook> search(Standingbook t) {
		return standingbookDao.search(t);
	}
	@Override
	public List<Standingbookterm> list() {
		return standingBookTermDao.getAll();
	}
	
	@Override
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean) {
		pageBean = standingBookTermDao.search(t, pageBean);
		return pageBean;
	}
	
	@Transactional("txManager")
	@Override
	public Standingbookterm insert(Standingbookterm standingbookterm) {
		standingBookTermDao.save(standingbookterm);
		return standingbookterm;
	}
}
