package net.ussoft.property.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.dao.ChargeitemDao;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.service.IBookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServce implements IBookService {
	@Resource
	private BookDao standingbookDao;
	
	@Resource
	private BooktermDao standingBookTermDao;
	@Resource
	private ChargeitemDao chargeitemDao;
	
	@Resource
	private BookDao bookDao;
	
	@Override
	public List<Book> search(Book t) {
		return standingbookDao.search(t);
	}
	@Override
	public List<Bookterm> list() {
		return standingBookTermDao.getAll();
	}
	
	@Override
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean) {
		pageBean = standingBookTermDao.search(t, pageBean);
		return pageBean;
	}
	
	@Transactional("txManager")
	@Override
	public Bookterm insert(Bookterm standingbookterm) {
		standingBookTermDao.save(standingbookterm);
		return standingbookterm;
	}
	@Override
	public PageBean<Book> list(Book t, PageBean<Book> pageBean) {
		return bookDao.search(t, pageBean);
	}
	@Override
	public List<Map<String,Object>> list(Book t) {
		StringBuffer sql = new StringBuffer("select * from book where 1=1");
		if(t.getUnitid()!=null && !"".equals(t.getUnitid())){
			sql.append(" and unitid='"+t.getUnitid()+"'");
		}
		if(t.getUnittermid()!=null && !"".equals(t.getUnittermid())){
			sql.append(" and unittermid='"+t.getUnittermid()+"'");
		}
		List<Object> values = new ArrayList<Object>();
		return bookDao.searchForMap(sql.toString(), values);
	}
	@Override
	public Boolean confirmReceived(Book t) {
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String datatime = format.format(new Date());
			StringBuffer str = new StringBuffer("update book set chargeovertime='"+datatime+"', chargestatus='1' where 1=1");
			if(t.getId()!=null && !"".equals(t.getId())){
				str.append(" and unitid='"+t.getId()+"'");
			}
			if(t.getUnittermid()!=null && !"".equals(t.getUnittermid())){
				str.append(" and unittermid='"+t.getUnittermid()+"'");
			}
			StringBuffer str1 = new StringBuffer("update chargenote set chargedate='"+datatime+"', chargestatus='1' where 1=1");
			if(t.getId()!=null && !"".equals(t.getId())){
				str1.append(" and unitid='"+t.getId()+"'");
			}
			if(t.getUnittermid()!=null && !"".equals(t.getUnittermid())){
				str1.append(" and unittermid='"+t.getUnittermid()+"'");
			}
			
			bookDao.execute(str.toString());
			bookDao.execute(str1.toString());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public int addChargeitem(String projectid,String unitid, String termid,String unittermid, String ids) {
		try{
			String[] addStrings = ids.split(",");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String chargetime = dateFormat.format(new Date());
			Chargeitem chargeitem = null;
			Book book;
			for (String id : addStrings) {
				book = new Book();
				chargeitem = new Chargeitem();
				chargeitem.setId(id);
				chargeitem = chargeitemDao.search(chargeitem).get(0);
				book.setId(UUID.randomUUID().toString());
				book.setProjectid(projectid);
				book.setUnitid(unitid);
				book.setChargetime(chargetime);//收费时间 由系统生成
				book.setItemcode(chargeitem.getItemcode());//收费项目名称
				book.setChargestatus(0);//默认的台帐状态为 未结束
				book.setTermid(unittermid);
				book.setUnittermid(unittermid);
				bookDao.save(book);
			}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
	@Override
	public int delete(String id) {
		return bookDao.del(id);
	}
	@Override
	public Book getById(String id) {
		return bookDao.get(id);
	}
	@Override
	public Book insert(Book book) {
		bookDao.save(book);
		return book;
	}
}
