package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IMeterService;

import org.springframework.stereotype.Service;

@Service
public class MeterService implements IMeterService{

	@Resource
	private MeterDao meterDao;
	@Override
	public List<Meter> list(Meter t) {
		return meterDao.search(t);
	}
	@Override
	public List<Meter> list() {
		return meterDao.getAll("meterdate");
	}
	@Override
	public PageBean<Meter> list(Meter t, PageBean<Meter> pageBean) {
		pageBean = meterDao.search(t, pageBean);
		return pageBean;
	}
	@Override
	public int update(Meter meter) {
		Meter m = meterDao.update(meter);
		if (null != m) {
			return 1;
		}
		return 0;
	}
	@Override
	public Meter getById(String id) {
		return meterDao.get(id);
	}

}
