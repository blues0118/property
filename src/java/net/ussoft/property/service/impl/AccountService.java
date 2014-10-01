package net.ussoft.property.service.impl;

/**
 * 与帐户有关操作service
 */

import java.util.List;

import javax.annotation.Resource;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.CodeDao;
import net.ussoft.property.dao.ConfigDao;
import net.ussoft.property.dao.RoleDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.service.IAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements IAccountService {
	
	@Resource
	private AccountDao accountDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private ConfigDao configDao;
	@Resource
	private CodeDao codeDao;
	

	@Override
	public Sys_account getById(String id) {
		return accountDao.get(id);
	}
	
	@Override
	public List<Sys_account> list() {
		return accountDao.getAll("accountcode");
	}
	
	@Override
	public PageBean<Sys_account> list(Sys_account t,PageBean<Sys_account> pageBean) {
		pageBean = accountDao.search(t, pageBean);
		return pageBean;
	}

	@Transactional("txManager")
	@Override
	public Sys_account insert(Sys_account account) {
		accountDao.save(account);
		return account;
	}

	@Transactional("txManager")
	@Override
	public int update(Sys_account account) {
		Sys_account tmp = accountDao.update(account);
		if (null != tmp) {
			return 1;
		}
		return 0;
	}

	@Transactional("txManager")
	@Override
	public int delete(String id) {
		
		return accountDao.del(id);
	}
	
	@Transactional("txManager")
	@Override
	public Boolean saverole(String id, String roleid) {
		//获取帐户对象
		Sys_account account = accountDao.get(id);
		
		if (null == account) {
			return false;
		}
		account.setRoleid(roleid);
		accountDao.update(account);
		
		return true;
	}

	@Transactional("txManager")
	@Override
	public Boolean removerole(String id, String roleid) {
		//获取帐户对象
		Sys_account account = accountDao.get(id);
		
		if (null == account) {
			return false;
		}
		account.setRoleid("");
		accountDao.update(account);
		
		return true;
	}

}
