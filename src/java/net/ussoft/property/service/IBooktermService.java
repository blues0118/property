package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Book;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unitterm;


public interface IBooktermService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Bookterm> search(Bookterm t);
	
	/**
	 * 分页读取台账信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Book> detailBookList(Book t,PageBean<Book> pageBean);
	
	/**
	 * 分页读取总台账账期信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean);
	
	/**
	 * 分页读取单元台账账期信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Unitterm> detailList(Unitterm t,PageBean<Unitterm> pageBean);
	
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
	

	
	/**
	 * 总账期列表用数据取得
	 * 
	 * @return List<Bookterm>
	 */
	public List<Bookterm> getTermList();
	/**
	 * 获取所有总账期列表
	 * 
	 * @return List<Bookterm>
	 */
	public List<Bookterm> getBooktermAllList();
	
	/**
	 * 状态改变
	 * @param barcodes
	 * @return
	 */
	public int changeStatus(String barcodes);
	
	/**
	 * 抄表数据件数
	 * @param id
	 * @return
	 */
	public int getMeteritemCnt(String id);
	
	/**
	 * 打印台账信息
	 * @param id
	 * @return
	 */
	public List<Book> printBookList(String id);
}
