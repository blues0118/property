package net.ussoft.property.service;

import net.ussoft.property.model.Equipment;
import net.ussoft.property.model.Otherpay;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Staff;
import net.ussoft.property.model.Staffcontent;


public interface IPayService {
	/**
	 * 插入设备管理
	 * @param equipment
	 * @return
	 */
	public Equipment insertEquipment(Equipment equipment);
	
	/**
	 * 取得设备管理信息
	 * @param projectId
	 * @param pageBean
	 * @return
	 */
	public PageBean<Equipment> getByProjetId(String projectId, PageBean<Equipment> pageBean);

	/**
	 * 更新设备
	 * @param equipment
	 * @return
	 */
	public int updateEquip(Equipment equipment);
	
	/**
	 * 删除设备
	 * @param id
	 * @return
	 */
	public int deleteEquip(String id);
	
	/**
	 * 取得设备
	 * @param id
	 * @return
	 */
	public Equipment getEquipById(String id);
	
	/**
	 * 插入员工管理
	 * @param staff
	 * @return
	 */
	public Staff insertStaff(Staff staff);
	
	/**
	 * 取得员工管理信息
	 * @param projectId
	 * @param pageBean
	 * @return
	 */
	public PageBean<Staff> getStaffList(Staff t, PageBean<Staff> pageBean);

	/**
	 * 更新员工
	 * @param staff
	 * @return
	 */
	public int updateStaff(Staff staff);
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	public int deleteStaff(String id);
	
	/**
	 * 取得员工
	 * @param id
	 * @return
	 */
	public Staff getStaffById(String id);
	
	/**
	 * 插入其他支出
	 * @param otherpay
	 * @return
	 */
	public Otherpay insertOther(Otherpay otherpay);
	
	/**
	 * 取得其他支出信息
	 * @param projectId
	 * @param pageBean
	 * @return
	 */
	public PageBean<Otherpay> getOtherList(Otherpay t, PageBean<Otherpay> pageBean);

	/**
	 * 更新其他支出
	 * @param staff
	 * @return
	 */
	public int updateOther(Otherpay otherpay);
	
	/**
	 * 删除其他支出
	 * @param id
	 * @return
	 */
	public int deleteOther(String id);
	
	/**
	 * 取得其他支出
	 * @param id
	 * @return
	 */
	public Otherpay getOtherById(String id);
	
	/**
	 * 插入员工工资
	 * @param staffcontent
	 * @return
	 */
	public Staffcontent insertStaffcontent(Staffcontent staffcontent);
	
	/**
	 * 取得员工工资管理信息
	 * @param staffid
	 * @param pageBean
	 * @return
	 */
	public PageBean<Staffcontent> getByStaffId(String staffid, PageBean<Staffcontent> pageBean);
	
	/**
	 * 取得员工工资
	 * @param id
	 * @return
	 */
	public Staffcontent getStaffcontentById(String id);
	
	/**
	 * 删除员工工资
	 * @param id
	 * @return
	 */
	public int deleteStaffcontent(String id);

	/**
	 * 更新员工工资
	 * @param staffcontent
	 * @return
	 */
	public int updateStaffcontent(Staffcontent staffcontent);

}
