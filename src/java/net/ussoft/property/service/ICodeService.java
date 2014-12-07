package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_code;

public interface ICodeService {

	/**
	 * 根据id，获取对象
	 * @param id
	 * @return
	 */
	public Sys_code getById(String id);
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Sys_code> list();
	
	/**
	 * 分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Sys_code> list(Sys_code t,PageBean<Sys_code> pageBean);
	
	/**
	 * 插入
	 * @param org
	 * @return
	 */
	public Sys_code insert(Sys_code code);
	
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	public int update(Sys_code code);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 根据对象，查list
	 * @param code
	 * @return
	 */
	public List<Sys_code> getByWhere(Sys_code code);
	
	/**
	 * 直接传where、order查
	 * @param where
	 * @param order
	 * @return
	 */
	public List<Sys_code> list(String where,List<Object> values,String order);
}
