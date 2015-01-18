package net.ussoft.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.EquipmentDao;
import net.ussoft.property.dao.OtherpayDao;
import net.ussoft.property.dao.StaffDao;
import net.ussoft.property.dao.StaffcontentDao;
import net.ussoft.property.model.Equipment;
import net.ussoft.property.model.Otherpay;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Staff;
import net.ussoft.property.model.Staffcontent;
import net.ussoft.property.service.IPayService;

import org.springframework.stereotype.Service;

@Service
public class PayService implements IPayService {

	@Resource
	private EquipmentDao equipmentDao;

	@Resource
	private StaffDao staffDao;

	@Resource
	private OtherpayDao otherpayDao;
	
	@Resource
	private StaffcontentDao staffcontentDao;
	
	@Override
	public Equipment insertEquipment(Equipment equipment) {
		equipmentDao.save(equipment);
		return equipment;
	}
	
	@Override
	public PageBean<Equipment> getByProjetId(String projectId, PageBean<Equipment> pageBean) {
		String sql = "select distinct te.* from bookterm tb, equipment te where te.projectid= '"
	                + projectId +"' and te.projectid=tb.projectid and tb.tremstatus=0";
		List<Object> sqlValues = new ArrayList<Object>();
		return equipmentDao.search(sql, sqlValues, pageBean);
	}

	/**
	 * 更新设备
	 * @param equipment
	 * @return
	 */
	public int updateEquip(Equipment equipment) {
		Equipment tmp = equipmentDao.update(equipment);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 删除设备
	 * @param id
	 * @return
	 */
	public int deleteEquip(String id) {
		return equipmentDao.del(id);
	}
	
	/**
	 * 取得设备
	 * @param id
	 * @return
	 */
	public Equipment getEquipById(String id) {
		return equipmentDao.get(id);
	}
	
	@Override
	public Staff insertStaff(Staff staff) {
		staffDao.save(staff);
		return staff;
	}
	
	@Override
	public PageBean<Staff> getStaffList(Staff t, PageBean<Staff> pageBean) {
		pageBean = staffDao.search(t, pageBean);
		return pageBean;
	}

	/**
	 * 更新员工
	 * @param equipment
	 * @return
	 */
	public int updateStaff(Staff staff) {
		Staff tmp = staffDao.update(staff);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	public int deleteStaff(String id) {
		return staffDao.del(id);
	}
	
	/**
	 * 取得员工
	 * @param id
	 * @return
	 */
	public Staff getStaffById(String id) {
		return staffDao.get(id);
	}
	
	@Override
	public Otherpay insertOther(Otherpay otherpay) {
		otherpayDao.save(otherpay);
		return otherpay;
		}
	
	@Override
	public PageBean<Otherpay> getOtherList(Otherpay t, PageBean<Otherpay> pageBean) {
		pageBean = otherpayDao.search(t, pageBean);
		return pageBean;
	}

	/**
	 * 更新其他支出
	 * @param equipment
	 * @return
	 */
	public int updateOther(Otherpay otherpay) {
		Otherpay tmp = otherpayDao.update(otherpay);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 删除其他支出
	 * @param id
	 * @return
	 */
	public int deleteOther(String id) {
		return otherpayDao.del(id);
	}
	
	/**
	 * 取得其他支出
	 * @param id
	 * @return
	 */
	public Otherpay getOtherById(String id) {
		return otherpayDao.get(id);
	}
	
	@Override
	public Staffcontent insertStaffcontent(Staffcontent staffcontent) {
		staffcontentDao.save(staffcontent);
		return staffcontent;
	}
	
	@Override
	public PageBean<Staffcontent> getByStaffId(String staffid, PageBean<Staffcontent> pageBean) {
		String sql = "select distinct te.* from bookterm tb, staffcontent te where te.staffid= '"
	                + staffid +"' and te.termid=tb.id and tb.tremstatus=0";
		List<Object> sqlValues = new ArrayList<Object>();
		return staffcontentDao.search(sql, sqlValues, pageBean);
	}
	
	/**
	 * 取得员工工资
	 * @param id
	 * @return
	 */
	public Staffcontent getStaffcontentById(String id) {
		return staffcontentDao.get(id);
	}
	
	/**
	 * 删除员工工资
	 * @param id
	 * @return
	 */
	public int deleteStaffcontent(String id) {
		return staffcontentDao.del(id);
	}

	/**
	 * 更新员工工资
	 * @param staffcontent
	 * @return
	 */
	public int updateStaffcontent(Staffcontent staffcontent) {
		Staffcontent tmp = staffcontentDao.update(staffcontent);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

}
