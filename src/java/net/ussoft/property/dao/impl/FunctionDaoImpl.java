package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.FunctionDao;
import net.ussoft.property.model.Sys_function;

import org.springframework.stereotype.Repository;

@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoMysqlImpl<Sys_function, String> implements FunctionDao {

	public FunctionDaoImpl() {
		super(Sys_function.class);
	}
}
