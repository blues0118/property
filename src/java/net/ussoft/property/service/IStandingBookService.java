package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbook;
import net.ussoft.property.model.Standingbookterm;
import net.ussoft.property.model.Sys_account;

public interface IStandingBookService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Standingbookterm> list();
	
	/**
	 * 总账期分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean);
	
	/**
	 * 插入总账期
	 * @param standingbookterm
	 * @return
	 */
	public Standingbookterm insert(Standingbookterm standingbookterm);
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Standingbook> search(Standingbook t);
}
