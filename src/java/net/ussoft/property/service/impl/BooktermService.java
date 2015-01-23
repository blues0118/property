package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.dao.UnitDao;
import net.ussoft.property.dao.UnittermDao;
import net.ussoft.property.model.Book;
import net.ussoft.property.model.Bookterm;
import net.ussoft.property.model.Meter;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Unit;
import net.ussoft.property.model.Unitterm;
import net.ussoft.property.service.IBooktermService;
import net.ussoft.property.util.CommonUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BooktermService implements IBooktermService {
	@Resource
	private BooktermDao booktermDao;
	
	@Resource
	private MeterDao meterDao;
	
	@Resource
	private UnittermDao unittermDao;
	
	@Resource
	private BookDao bookDao;
	
	@Resource
	private UnitDao unitDao;

	@Override
	public Bookterm getById(String id) {
		return booktermDao.get(id);
	}
	
	@Override
	public List<Bookterm> search(Bookterm t) {
		return booktermDao.search(t);
	}
	
	/**
	 * 分页读取台账信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Book> detailBookList(Book t,PageBean<Book> pageBean) {
		pageBean = bookDao.search(t, pageBean);
		return pageBean;
	}
	
	/**
	 * 分页读取单元台账账期信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Unitterm> detailList(Unitterm t,PageBean<Unitterm> pageBean) {
		pageBean = unittermDao.search(t, pageBean);
		return pageBean;
	}
	
	@Override
	public PageBean<Bookterm> list(Bookterm t,PageBean<Bookterm> pageBean) {
		if (t.getTermcode() == null) {
			return booktermDao.search(t, pageBean);
		} else {
			String sql = "select * from bookterm where projectid= '"
		                + t.getProjectid() +"' and termcode like '" + t.getTermcode() + "%'";
			List<Object> sqlValues = new ArrayList<Object>();
			return booktermDao.search(sql, sqlValues, pageBean);
		}
		
	}
	
	@Transactional("txManager")
	public Bookterm insertBookTerm(Bookterm bookterm, Sys_account accountSession) {
		booktermDao.save(bookterm);
		insertUnitterm(bookterm);
		insertMeter(bookterm, accountSession);
		return bookterm;
	}

	@Transactional("txManager")
	@Override
	public int update(Bookterm bookterm) {
		Bookterm tmp = booktermDao.update(bookterm);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		
		return booktermDao.del(id);
	}


	/**
	 * 抄表管理插入
	 * 
	 * @param bookterm
	 * @param accountSession
	 */
	private void insertMeter(Bookterm bookterm, Sys_account accountSession) {
		// 抄表管理插入
		Meter meter = new Meter();
		// id
		meter.setId(UUID.randomUUID().toString());
		// 抄表账期 :创建操作默认为操作时的年月。
		meter.setMeterdate(bookterm.getTermcode()); // TODO
		// 项目id
		meter.setProjectid(bookterm.getProjectid());
		// 操作人id
		meter.setAccountid(accountSession.getAccountcode());
		// 操作人姓名
		meter.setAccountname(accountSession.getAccountname());
		// 抄表人
		meter.setMeterman("");
		// 创建时间
		meter.setCreatetime(CommonUtils.getTimeStamp());
		// 备注
		meter.setMetermemo("");
		// 状态  ：0:未入台账  1:已入台账
		meter.setMeterstatus(0);
		// 总台账帐期id
		meter.setTermid(bookterm.getId());
		meterDao.save(meter);
	}

	/**
	 * 单元台账账期插入
	 * 
	 * @param standingbookterm
	 */
	private void insertUnitterm(Bookterm standingbookterm) {
		// 单元台账账期插入
		Unitterm unitterm = new Unitterm();
		// 账期名称
		unitterm.setUnittermcode(standingbookterm.getTermcode());
		// 总账期id
		unitterm.setTermid(standingbookterm.getId());
		// 备注
		unitterm.setUnittermmemo("");;
		// 项目id
		unitterm.setProjectid(standingbookterm.getProjectid());
		// 单元账期状态
		unitterm.setUnittermstatus(0);
		List<Unit> unitList = getByProjectId(standingbookterm.getProjectid());
		for (int i = 0; i < unitList.size(); i++) {
			// id
			unitterm.setId(UUID.randomUUID().toString());
			Unit unit = unitList.get(i);
			// 单元id
			unitterm.setUnitid(unit.getId());
			unittermDao.save(unitterm);
		}
	}
	
	/**
	 * 单元管理表的Id取得
	 * 
	 * @param projectId 所属项目
	 * @return List<Unit>
	 */
	private List<Unit> getByProjectId(String projectId) {
		String sql = "select id from unit where projeuctid=? ";
		List<Object> sqlValues = new ArrayList<Object>();
		sqlValues.add(projectId);
		return unitDao.search(sql , sqlValues);
	}
	
	/**
	 * 总账期列表用数据取得
	 * 
	 * @return List<Bookterm>
	 */
	public List<Bookterm> getTermList() {
		String sql = "select id,termcode from bookterm where tremstatus=0 ";
		List<Object> sqlValues = new ArrayList<Object>();
		return booktermDao.search(sql , sqlValues);
	}
	/**
	 * 获取所有总账期列表
	 * 
	 * @return List<Bookterm>
	 */
	public List<Bookterm> getBooktermAllList() {
		String sql = "select id,termcode from bookterm ";
		List<Object> sqlValues = new ArrayList<Object>();
		return booktermDao.search(sql , sqlValues);
	}
	
}
