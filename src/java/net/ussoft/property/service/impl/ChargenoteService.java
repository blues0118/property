package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.dao.ChargenoteDao;
import net.ussoft.property.model.Chargenote;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IChargenoteService;

@Service
public class ChargenoteService implements IChargenoteService {
	@Resource
	private ChargenoteDao chargenoteDao;
	@Override
	public PageBean<Chargenote> list(Chargenote t, PageBean<Chargenote> pageBean) {
		pageBean = chargenoteDao.search(t, pageBean);
		return pageBean;
	}
	@Override
	public List<Chargenote> list(Chargenote chargenote) {
		// TODO Auto-generated method stub
		return chargenoteDao.search(chargenote);
	}

}
