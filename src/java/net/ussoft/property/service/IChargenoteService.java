package net.ussoft.property.service;

import net.ussoft.property.model.Chargenote;
import net.ussoft.property.model.PageBean;

public interface IChargenoteService {
	/**
	 * 分页读取收费项
	 * @param pageBean
	 * @return
	 */
	public PageBean<Chargenote> list(Chargenote t,PageBean<Chargenote> pageBean);
	
}
