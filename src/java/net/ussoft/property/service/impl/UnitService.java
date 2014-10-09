package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.UnitDao;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.IUnitService;

import org.springframework.stereotype.Service;


@Service
public class UnitService implements IUnitService {
	
	
	@Resource
	private UnitDao unitDao;
	
	@Override
	public List<Unit> search(Unit t) {
		return unitDao.search(t);
	}
	
	@Override
	public Unit insert(Unit unit) {
		return unitDao.save(unit);
	}

}
