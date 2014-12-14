package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbookterm;
import net.ussoft.property.service.IStandingbooktermService;

@Service
public class StandingbooktermService implements IStandingbooktermService {
	@Resource
	private BooktermDao standingbooktermDao;
	@Override
	public List<Standingbookterm> search(Standingbookterm t) {
		return standingbooktermDao.search(t);
	}
	@Override
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean) {
		return standingbooktermDao.search(t, pageBean);
	}

}
