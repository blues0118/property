package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbookterm;

public interface IStandingBookService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Standingbookterm> list();
	
	/**
	 * 分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean);
}
