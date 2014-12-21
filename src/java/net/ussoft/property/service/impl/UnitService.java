package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.dao.LeaseAgreementDao;
import net.ussoft.property.dao.LeaseDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.dao.UnitDao;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.Sys_role;
import net.ussoft.property.model.Unit;
import net.ussoft.property.service.IUnitService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UnitService implements IUnitService {
	
	
	@Resource
	private UnitDao unitDao;
	
	@Resource
	private LeaseDao leaseDao;
	
	@Resource
	private LeaseAgreementDao leaseAgreementDao;
	
	@Resource
	private MeteritemDao meterItemDao;
	
	@Resource
	private ChargeitemDao chargeitemDao;
	
	@Override
	public List<Unit> search(Unit t) {
		return unitDao.search(t);
	}
	
	@Override
	public Unit insert(Unit unit) {
		return unitDao.save(unit);
	}
	@Transactional("txManager")
	@Override
	public int delete(String ids) {
		
		String[] delStrings = ids.split(",");
		for (String id : delStrings) {
			unitDao.del(id);
		}
		return 1;
	}
	@Override
	public Unit getById(String id) {
		return unitDao.get(id);
	}
	
	@Transactional("txManager")
	@Override
	public int update(Unit unit) {
		Unit tmp = unitDao.update(unit);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

}
