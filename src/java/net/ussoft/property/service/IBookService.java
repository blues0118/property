package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.PageBean;

public interface IBookService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Bookterm> list();
	
	/**
	 * 总账期分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean);
	
	/**
	 * 插入总账期
	 * @param standingbookterm
	 * @return
	 */
	public Bookterm insert(Bookterm standingbookterm);
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Book> search(Book t);
}
