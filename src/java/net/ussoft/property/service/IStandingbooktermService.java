package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Standingbookterm;


public interface IStandingbooktermService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Standingbookterm> search(Standingbookterm t);
	/**
	 * 分页读取账期
	 * @param pageBean
	 * @return
	 */
	public PageBean<Standingbookterm> list(Standingbookterm t,PageBean<Standingbookterm> pageBean);
}
