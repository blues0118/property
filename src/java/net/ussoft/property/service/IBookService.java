package net.ussoft.property.service;

import java.util.List;
import java.util.Map;

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
	 * 单元台帐分页读取
	 * @param pageBean
	 * @return
	 */
	public PageBean<Book> list(Book t,PageBean<Book> pageBean);
	
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
	/**
	 * 增加单元台帐
	 * @return
	 */
	public int addChargeitem(String projectid,String unitid,String termid,String unittermid,String ids);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id);
	/**
	 * 取得单元台账账期
	 * @param id
	 * @return
	 */
	public Book getById(String id);
	
	/**
	 * 插入总账期
	 * @param standingbookterm
	 * @return
	 */
	public Book insert(Book book);
	
	public Boolean confirmReceived(Book t);
	public List<Map<String,Object>> list(Book t);
	
}
