package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.CodeDao;
import net.ussoft.property.model.Sys_code;

import org.springframework.stereotype.Repository;

@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoMysqlImpl<Sys_code, String> implements CodeDao {

	public CodeDaoImpl() {
		super(Sys_code.class);
	}
}
