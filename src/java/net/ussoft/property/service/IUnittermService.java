package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Unitterm;


public interface IUnittermService {
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Unitterm> search(Unitterm t);
	/**
	 * 按照单元id，按照unittermcode排序查询信息
	 * @return
	 */
	public List<Unitterm> search(String unitid);
}
