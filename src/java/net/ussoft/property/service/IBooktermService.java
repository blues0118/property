package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Sys_account;


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
	
	/**
	 * 插入总台账账期
	 * @param bookterm
	 * @param accountSession
	 * @return
	 */
	public Bookterm insertBookTerm(Bookterm bookterm, Sys_account accountSession);

	/**
	 * 更新总台账账期
	 * @param bookterm
	 * @return
	 */

	public int update(Bookterm bookterm);
	
	/**
	 * 删除总台账账期
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 取得总台账账期
	 * @param id
	 * @return
	 */
	public Bookterm getById(String id);
}
