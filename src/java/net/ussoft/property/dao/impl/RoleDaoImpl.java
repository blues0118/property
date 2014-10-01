package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.RoleDao;
import net.ussoft.property.model.Sys_role;

import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoMysqlImpl<Sys_role, String> implements RoleDao {

	public RoleDaoImpl() {
		super(Sys_role.class);
	}
}
