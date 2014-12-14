package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Bookterm;


public interface IBooktermService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Bookterm> search(Bookterm t);
	/**
	 * 分页读取账期
	 * @param pageBean
	 * @return
	 */
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean);
}
