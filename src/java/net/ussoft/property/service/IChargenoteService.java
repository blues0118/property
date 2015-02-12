package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.Chargeitem;
import net.ussoft.property.model.Chargenote;
import net.ussoft.property.model.PageBean;

public interface IChargenoteService {
	/**
	 * 分页读取收费项
	 * @param pageBean
	 * @return
	 */
	public PageBean<Chargenote> list(Chargenote t,PageBean<Chargenote> pageBean);
	/**
	 * 根据条件，获取全部信息
	 * @return
	 */
	public List<Chargenote> list(Chargenote chargenote);
	
}
