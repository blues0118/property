package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Meter;
import net.ussoft.property.model.PageBean;


public interface IMeterService {
	public List<Meter> list(Meter t);
	public List<Meter> list();
	
	/**
	 * 分页读取信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Meter> list(Meter t,PageBean<Meter> pageBean);
	
	/**
	 * 更新
	 * @param meter
	 * @return
	 */
	public int update(Meter meter);
}
