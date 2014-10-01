package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.RoleFunDao;
import net.ussoft.property.model.Sys_role_fun;

import org.springframework.stereotype.Repository;

@Repository("rolefunctionDao")
public class RoleFunDaoImpl extends BaseDaoMysqlImpl<Sys_role_fun, String> implements RoleFunDao {

	public RoleFunDaoImpl() {
		super(Sys_role_fun.class);
	}
}
