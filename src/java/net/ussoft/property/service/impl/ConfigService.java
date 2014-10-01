package net.ussoft.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.ConfigDao;
import net.ussoft.property.model.Sys_config;
import net.ussoft.property.service.IConfigService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigService implements IConfigService {
	
	@Resource
	private ConfigDao configDao;

	@Override
	public Sys_config getById(String id) {
		return configDao.get(id);
	}
	
	@Override
	public List<Sys_config> list() {
		return configDao.getAll();
	}

	@Transactional("txManager")
	@Override
	public int update(Sys_config config) {
		Sys_config tmp = configDao.update(config);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public Sys_config insert(Sys_config config) {
		if (null == config) {
			return null;
		}
		return configDao.save(config);
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		return configDao.del(id);
	}

}
