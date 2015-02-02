package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.ussoft.property.dao.BookDao;
import net.ussoft.property.dao.BooktermDao;
import net.ussoft.property.dao.ChargenoteDao;
import net.ussoft.property.dao.EquipmentcontentDao;
import net.ussoft.property.dao.MeterDao;
import net.ussoft.property.dao.MeteritemDao;
import net.ussoft.property.dao.OtherpayDao;
import net.ussoft.property.dao.StaffcontentDao;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BooktermService implements IBooktermService {
	@Resource
	private BooktermDao booktermDao;
	
	@Resource
	private MeterDao meterDao;
	
	@Resource
	private MeteritemDao meteritemDao;
	
	@Resource
	private EquipmentcontentDao equipmentcontentDao;
	
	@Resource
	private UnittermDao unittermDao;
	
	@Resource
	private BookDao bookDao;
	
	@Resource
	private UnitDao unitDao;

	@Resource
	private OtherpayDao otherpayDao;
	
	@Resource
	private StaffcontentDao staffcontentDao;
	
	@Resource
	private ChargenoteDao chargenoteDao;
	
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
		//pageBean = unittermDao.search(t, pageBean);
		//return pageBean;

		String sql = "select distinct ut.id id, u.unitcode unittermcode, ut.termid, ut.projectid, ut.unitid, ut.unittermstatus from unitterm ut, unit u where ut.unitid=u.id and ut.termid=?";
		List<Object> sqlValues = new ArrayList<Object>();
		sqlValues.add(t.getTermid());
		return unittermDao.search(sql, sqlValues, pageBean);
	
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
		// 收款单
		String sqlc = "delete from chargenote where id in (select distinct noteid from book where termid = ?)";
		List<Object> sqlcValues = new ArrayList<Object>();
		sqlcValues.add(id);
		chargenoteDao.del(sqlc , sqlcValues);
		
		// 台账
		String sqlb = "delete from book where termid = ?";
		List<Object> sqlbValues = new ArrayList<Object>();
		sqlbValues.add(id);
		bookDao.del(sqlb , sqlbValues);
		
		// 单元台账账期删除
		String sqlUt = "delete from unitterm where termid = ?";
		List<Object> sqlUtValues = new ArrayList<Object>();
		sqlUtValues.add(id);
		unittermDao.del(sqlUt , sqlUtValues);
		
		// 抄表内容
		String sqlmi = "delete from meteritem where meterid in (select distinct id from meter where termid = ?)";
		List<Object> sqlmiValues = new ArrayList<Object>();
		sqlmiValues.add(id);
		meteritemDao.del(sqlmi , sqlmiValues);
		
		// 抄表管理删除
		String sqlm = "delete from meter where termid = ?";
		List<Object> sqlmValues = new ArrayList<Object>();
		sqlmValues.add(id);
		meterDao.del(sqlm , sqlmValues);
		
		// 设备维护内容
		String sqlec = "delete from equipmentcontent where termid = ?";
		List<Object> sqlecValues = new ArrayList<Object>();
		sqlecValues.add(id);
		equipmentcontentDao.del(sqlec , sqlecValues);
		
		// 员工支出
		String sqlsc = "delete from staffcontent where termid = ?";
		List<Object> sqlscValues = new ArrayList<Object>();
		sqlscValues.add(id);
		staffcontentDao.del(sqlsc , sqlscValues);
		
		// 其他支出
		String sqlo = "delete from otherpay where termid = ?";
		List<Object> sqloValues = new ArrayList<Object>();
		sqloValues.add(id);
		otherpayDao.del(sqlo , sqloValues);
		
		// 总台账账期删除
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
		meter.setMeterdate(CommonUtils.getTimeStamp().substring(0, 10));
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
	 * 状态改变
	 * @param barcodes
	 * @return
	 */
	public int changeStatus(String barcodes) {
		
        String[] codeStrings = barcodes.split(",");
		
		for (String code : codeStrings) {
			// 收款单状态改变     更改状态1
			String sqlcn = "update chargenote set chargestatus = 1 where barcode = ?";
			List<Object> sqlcnValues = new ArrayList<Object>();
			sqlcnValues.add(code);
			chargenoteDao.update(sqlcn , sqlcnValues);
			
			// 台账状态改变     更改状态2
			String sqlb = "update book set chargestatus = 1 where noteid = (select id from chargenote where barcode = ?)";
			List<Object> sqlbValues = new ArrayList<Object>();
			sqlbValues.add(code);
			bookDao.update(sqlb , sqlbValues);
			
			// 台账状态为未结束的件数
			String sqlbcnt = "select count(bb.id) from book bb where bb.chargestatus = 0 "
					+ "and bb.projectid||bb.unitid||bb.termid||bb.unittermid=(select bbc.projectid||bbc.unitid||bbc.termid||bbc.unittermid "
					+ "from book bbc, chargenote cb where cb.barcode = ? and bbc.noteid = cb.id)";
			List<Object> sqlbcntValues = new ArrayList<Object>();
			sqlbcntValues.add(code);
			int cntbook = bookDao.getInt(sqlbcnt , sqlbcntValues);
			
			// 件数等0的时候
			if (cntbook == 0) {
				// 单元台账状态更新     更改状态3
				String sqlu = "update unitterm set unittermstatus = 1 "
						+ "where id = (select bbc.unittermid from book bbc, chargenote cb where cb.barcode = ? and bbc.noteid = cb.id)";
				List<Object> sqluValues = new ArrayList<Object>();
				sqluValues.add(code);
				unittermDao.update(sqlu, sqluValues);
			}
			
			// 台账状态为未结束的件数
			String sqlbtcnt = "select count(bb.id) from unitterm bb where bb.unittermstatus = 0 "
					+ "and bb.termid=(select bbc.termid from book bbc, chargenote cb where cb.barcode = ? and bbc.noteid = cb.id)";
			List<Object> sqlbtcntValues = new ArrayList<Object>();
			sqlbtcntValues.add(code);
			int cntbookt = unittermDao.getInt(sqlbtcnt , sqlbtcntValues);
			
			// 件数等0的时候
			if (cntbookt == 0) {
				// 单元台账状态更新     更改状态4
				String sqlbt = "update bookterm set tremstatus = 1 "
						+ "where id = (select bbc.termid from book bbc, chargenote cb where cb.barcode = ? and bbc.noteid = cb.id)";
				List<Object> sqlbtValues = new ArrayList<Object>();
				sqlbtValues.add(code);
				unittermDao.update(sqlbt, sqlbtValues);
			}
		}
		
		return 1;
		
		
		
	}
	
	/**
	 * 抄表数据件数
	 * @param id
	 * @return
	 */
	public int getMeteritemCnt(String id) {
		String sqlbcnt = "select count(bb.id) from meteritem bb, meter bm where bb.meterid = bm.id and bm.termid = ?";
		List<Object> sqlbcntValues = new ArrayList<Object>();
		sqlbcntValues.add(id);
		int cntbook = meterDao.getInt(sqlbcnt , sqlbcntValues);
		Log log = LogFactory.getLog(this.getClass());
		log.info("cntbook:"+cntbook);
		return cntbook;
	}
	
	/**
	 * 打印台账信息
	 * @param id
	 * @return
	 */
	public List<Book> printBookList(String id) {
		String sql = "select bb.unitid, bb.noteid, bb.itemcode,bb.lastnumber, bb.newnumber, bb.chargesum, bb.bookmemo, tt.cnt unittermid from book bb, "
				+ "(select unitid, noteid,  count(unitid)  cnt from book where termid = ? group by unitid, noteid) tt"
				+ " where bb.unitid=tt.unitid and bb.noteid=tt.noteid order by bb.unitid, bb.noteid;";
		List<Object> sqlValues = new ArrayList<Object>();
		sqlValues.add(id);
		return bookDao.search(sql , sqlValues);
	}
	
}
