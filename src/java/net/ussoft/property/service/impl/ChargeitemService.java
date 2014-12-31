package net.ussoft.property.service.impl;

/**
 * 收费项目管理
 */

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IChargeitemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargeitemService implements IChargeitemService {
	
	@Resource
	private ChargeitemDao chargeitemDao;
	

	@Override
	public Chargeitem getById(String id) {
		return chargeitemDao.get(id);
	}
	
	@Override
	public List<Chargeitem> list() {
		return chargeitemDao.getAll("accountcode");
	}
	
	@Override
	public PageBean<Chargeitem> list(Chargeitem t,PageBean<Chargeitem> pageBean) {
		pageBean = chargeitemDao.search(t, pageBean);
		return pageBean;
	}

	@Transactional("txManager")
	@Override
	public Chargeitem insert(Chargeitem chargeitem) {
		chargeitemDao.save(chargeitem);
		return chargeitem;
	}

	@Transactional("txManager")
	@Override
	public int update(Chargeitem chargeitem) {
		Chargeitem tmp = chargeitemDao.update(chargeitem);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		
		return chargeitemDao.del(id);
	}
	@Override
	public List<Chargeitem> list(Chargeitem chargeitem) {
		return chargeitemDao.search(chargeitem);
	}
	@Transactional("txManager")
	@Override
	public int addChargeitem(String unitid, String ids) {
		try{
			String[] addUnitStrings = unitid.split(",");
			String[] addStrings = ids.split(",");
			Chargeitem chargeitem = null;
			Chargeitem chargeitemNew = null;
			for(String unitidi:addUnitStrings){
				for (String id : addStrings) {
					chargeitem = new Chargeitem();
					chargeitem.setId(id);
					chargeitemNew = chargeitemDao.search(chargeitem).get(0);
					chargeitemNew.setId(UUID.randomUUID().toString());
					chargeitemNew.setUnitid(unitidi);
					chargeitemDao.save(chargeitemNew);
				}
			}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
}
