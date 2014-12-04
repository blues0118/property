package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;



import net.ussoft.property.dao.StandingbooktermDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbookterm;
import net.ussoft.property.service.IStandingBookService;

import org.springframework.stereotype.Service;


@Service
public class StandingBookServce implements IStandingBookService {
	
	@Resource
	private StandingbooktermDao standingBookTermDao;

	@Override
	public List<Standingbookterm> list() {
		return standingBookTermDao.getAll();
	}
	
	@Override
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean) {
		pageBean = standingBookTermDao.search(t, pageBean);
		return pageBean;
	}
}
