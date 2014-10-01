package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Sys_account;

public interface IAccountService {
	
	/**
	 * 获取帐户
	 * @param id
	 * @return
	 */
	public Sys_account getById(String id);
	
	/**
	 * 获取全部信息。按帐户名称排序
	 * @return
	 */
	public List<Sys_account> list();
	
	/**
	 * 分页读取账户信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Sys_account> list(Sys_account t,PageBean<Sys_account> pageBean);
	
	/**
	 * 插入新帐户
	 * @param account
	 * @return
	 */
	public Sys_account insert(Sys_account account);
	
	/**
	 * 更新帐户
	 * @param account
	 * @return
	 */
	public int update(Sys_account account);
	/**
	 * 删除帐户
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 设置帐户的角色
	 * @param id
	 * @param roleid
	 * @return
	 */
	public Boolean saverole(String id,String roleid);
	
	/**
	 * 移除帐户的角色
	 * @param id
	 * @param roleid
	 * @return
	 */
	public Boolean removerole(String id,String roleid);
	
}
