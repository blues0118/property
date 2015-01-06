package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IMeterItemService;

import org.springframework.stereotype.Service;

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
	@Override
	public int delete(String id) {
		return meterItemDao.del(id);
	}
	@Override
	public Meteritem getById(String id) {
		return meterItemDao.get(id);
	}
	@Override
	public Meteritem insert(Meteritem meteritem) {
		meterItemDao.save(meteritem);
		return meteritem;
	}
	@Override
	public int update(Meteritem meteritem) {
		Meteritem m = meterItemDao.update(meteritem);
		if(m != null)
			return 1;
		return 0;
	}
	@Override
	public List<Meteritem> unitGroupList(String meterid,String projectid) {
		List<Object> values=new ArrayList<Object>();
		values.add(meterid);
		values.add(projectid);
		
		String sql = "SELECT * FROM meteritem WHERE meterid=? AND projectid=? GROUP BY unitid";
		List<Meteritem> meteritemList = meterItemDao.search(sql, values);
		return meteritemList;
	}
	@Override
	public List<Meteritem> unitChargeList(String meterid, String projectid,
			String unitid) {
		List<Object> values=new ArrayList<Object>();
		values.add(meterid);
		values.add(projectid);
		values.add(unitid);
		
		String sql = "SELECT * FROM meteritem WHERE meterid=? AND projectid=? AND unitid=?";
		List<Meteritem> meteritemList = meterItemDao.search(sql, values);
		return meteritemList;
	}

}
