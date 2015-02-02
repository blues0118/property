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
	/**
	 * 删除选中的单元信息
	 * @param unit
	 * @return
	 */
	public int delete(String ids);
	/**
	 * 查看或修改选中的单元信息
	 * @param unit
	 * @return
	 */
	public Unit getById(String id);
	/**
	 * 跟新单元信息的id
	 * @param unit
	 * @return
	 */
	public int update(Unit unit);
	
}
