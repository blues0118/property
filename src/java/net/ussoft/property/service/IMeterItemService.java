package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Meter;
import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;

public interface IMeterItemService {
	/**
	 * 分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Meteritem> list(Meteritem t,PageBean<Meteritem> pageBean);
	public List<Meteritem> list(Meteritem t);
}
