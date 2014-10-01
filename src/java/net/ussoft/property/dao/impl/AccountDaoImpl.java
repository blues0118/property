package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.model.Sys_account;

import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoMysqlImpl<Sys_account, String> implements AccountDao {

	public AccountDaoImpl() {
		super(Sys_account.class);
	}
}
