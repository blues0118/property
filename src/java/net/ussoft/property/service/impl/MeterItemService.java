package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IMeterItemService;

@Service
public class MeterItemService implements IMeterItemService {
	@Resource
	private MeteritemDao meterItemDao;
	@Override
	public PageBean<Meteritem> list(Meteritem t, PageBean<Meteritem> pageBean) {
		pageBean = meterItemDao.search(t, pageBean);
		return pageBean;
	}
	@Override
	public List<Meteritem> list(Meteritem t) {
		return meterItemDao.search(t);
	}

}
