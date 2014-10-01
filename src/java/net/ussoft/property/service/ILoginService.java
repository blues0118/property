package net.ussoft.property.service;

import net.ussoft.property.model.Sys_account;

/**
 * 系统登录服务层
 * @author wangf
 *
 */
public interface ILoginService {
	

	/**
	 * 系统注册验证。判断当前系统是否注册或注册是否正确。
	 * @return	true or false
	 * @throws Exception 
	 */
	public Boolean regist() throws Exception;
	
	/**
	 * 获取服务器的mac地址。
	 * @return
	 */
	public String getLocalMacAddress();
	
	/**
	 * 生成注册码
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public String getRegistCode(String str) throws Exception;
	
	/**
	 * 帐户登陆验证
	 * @param account
	 * @return
	 */
	public Sys_account login(Sys_account account);

}
