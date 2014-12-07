package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Project;
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

}
