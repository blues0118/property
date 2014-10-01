package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Sys_init;

public interface IInitService {

	/**
	 * 根据id，获取对象
	 * @param id
	 * @return
	 */
	public Sys_init getById(String id);
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Sys_init> list();
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	public int update(Sys_init init);
	
	/**
	 * 根据对象值，查完整对象
	 * @param init
	 * @return
	 */
	public Sys_init getByWhere(Sys_init init);

	/**
	 * 插入
	 * @param init
	 * @return
	 */
	Sys_init insert(Sys_init init);

}
