package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.InitDao;
import net.ussoft.property.model.Sys_init;
import net.ussoft.property.service.IInitService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InitService implements IInitService {

	@Resource
	private InitDao initDao;
	@Override
	public Sys_init getById(String id) {
		return initDao.get(id);
	}

	@Override
	public List<Sys_init> list() {
		return initDao.getAll();
	}

	@Transactional("txManager")
	@Override
	public Sys_init insert(Sys_init init) {
		Sys_init tmp = initDao.save(init);
		return tmp;
	}
	
	@Transactional("txManager")
	@Override
	public int update(Sys_init init) {
		Sys_init tmp = initDao.update(init);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Override
	public Sys_init getByWhere(Sys_init init) {
		if (null == init) {
			return null;
		}
		List<Sys_init> initList = initDao.search(init);
		
		if (null != initList && initList.size() == 1) {
			return initList.get(0);
		}
		return null;
	}

}
