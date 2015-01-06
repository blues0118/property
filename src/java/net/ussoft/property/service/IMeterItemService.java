package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Meteritem;
import net.ussoft.property.model.PageBean;

public interface IMeterItemService {
	/**
	 * 分页读抄表记录信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Meteritem> list(Meteritem t,PageBean<Meteritem> pageBean);
	public List<Meteritem> list(Meteritem t);
	
	/**
	 * 根据抄表账期ID和项目ID获取抄表记录中的单元
	 * */
	public List<Meteritem> unitGroupList(String meterid,String projectid);
	
	/**
	 * 根据抄表账期ID、项目ID、单元ID获取抄表记录中的单元收费项
	 * */
	public List<Meteritem> unitChargeList(String meterid,String projectid,String unitid);
	
	
	/**
	 * 获取抄表记录
	 * @param id
	 * @return
	 */
	public Meteritem getById(String id);
	
	/**
	 * 添加抄表记录
	 * @param chargeitem
	 * @return
	 */
	public Meteritem insert(Meteritem meteritem);
	
	/**
	 * 更新抄表记录
	 * @param meteritem
	 * @return
	 */
	public int update(Meteritem meteritem);
	/**
	 * 删除抄表记录
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
}
