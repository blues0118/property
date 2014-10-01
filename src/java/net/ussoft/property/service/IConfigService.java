package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Sys_config;

public interface IConfigService {

	/**
	 * 根据id，获取对象
	 * @param id
	 * @return
	 */
	public Sys_config getById(String id);
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Sys_config> list();
	
	
	/**
	 * 插入
	 * @param org
	 * @return
	 */
	public Sys_config insert(Sys_config config);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	public int update(Sys_config config);
	
	
}
