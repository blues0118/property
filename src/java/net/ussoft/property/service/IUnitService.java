package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Unit;

/**
 * 单元管理service
 * @author hex
 *
 */
public interface IUnitService {
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Unit> search(Unit t);
	/**
	 * 插入单元信息
	 * @param unit
	 * @return
	 */
	public Unit insert(Unit unit);
	
	
	
}
