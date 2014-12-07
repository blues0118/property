package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.PageBean;

public interface IChargeitemService {
	
	/**
	 * 获取收费项
	 * @param id
	 * @return
	 */
	public Chargeitem getById(String id);
	
	/**
	 * 获取全部收费项
	 * @return
	 */
	public List<Chargeitem> list();
	
	/**
	 * 分页读取收费项
	 * @param pageBean
	 * @return
	 */
	public PageBean<Chargeitem> list(Chargeitem t,PageBean<Chargeitem> pageBean);
	
	/**
	 * 添加收费项
	 * @param chargeitem
	 * @return
	 */
	public Chargeitem insert(Chargeitem chargeitem);
	
	/**
	 * 更新
	 * @param chargeitem
	 * @return
	 */
	public int update(Chargeitem chargeitem);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id);

}
