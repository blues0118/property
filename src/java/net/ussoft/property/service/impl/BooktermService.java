package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IBooktermService;

import org.springframework.stereotype.Service;

@Service
public class BooktermService implements IBooktermService {
	@Resource
	private BooktermDao standingbooktermDao;
	@Override
	public List<Bookterm> search(Bookterm t) {
		return standingbooktermDao.search(t);
	}
	@Override
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean) {
		return standingbooktermDao.search(t, pageBean);
	}

}
