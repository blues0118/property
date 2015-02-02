package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.UnittermDao;
import net.ussoft.property.model.Unitterm;
import net.ussoft.property.service.IUnittermService;

@Service
public class UnittermService implements IUnittermService {
	
	@Resource
	private UnittermDao unittermDao;
	
	@Override
	public List<Unitterm> search(Unitterm t) {
		return unittermDao.search(t);
	}

	@Override
	public List<Unitterm> search(String unitid) {
		String sql = " select * from unitterm where unitid=? order by unittermcode desc";
		List<Object> sqlValues = new ArrayList<Object>();
		sqlValues.add(unitid);
		return unittermDao.search(sql, sqlValues);
	}

}
