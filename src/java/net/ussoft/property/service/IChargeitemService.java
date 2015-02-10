package net.ussoft.property.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 根据条件，获取全部信息
	 * @return
	 */
	public List<Chargeitem> list(Chargeitem chargeitem);
	/**
	 * 为单元增加收费项目
	 * @param id
	 * @return
	 */
	public int addChargeitem(String unitid,String ids);
	/**
	 * 查询提醒收费的项目
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> list(String projeuctid,String unitid,String isWatch);
}
