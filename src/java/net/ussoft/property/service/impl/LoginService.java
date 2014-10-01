package net.ussoft.property.service.impl;

/**
 * 系统登录、注册码
 */

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ussoft.property.dao.AccountDao;
import net.ussoft.property.dao.InitDao;
import net.ussoft.property.model.Sys_account;
import net.ussoft.property.model.Sys_init;
import net.ussoft.property.service.ILoginService;
import net.ussoft.property.util.EncryptionDecryption;
import net.ussoft.property.util.MD5;
import net.ussoft.property.util.SystemTool;

@Service
public class LoginService implements ILoginService {
	
	@Resource
	private InitDao initDao;
	@Resource
	private AccountDao accountDao;

	@Override
	public Boolean regist() throws Exception {
		Boolean result = false;
		String mac = getLocalMacAddress();
		
		String tmp = getRegistCode(mac);
		
		EncryptionDecryption des = new EncryptionDecryption();
		String createRegistCode = des.encrypt(tmp);
		
		//检查系统注册，如果注册不对，转到注册页.
		Sys_init init = new Sys_init();
		init.setInitkey("registcode");
		init = initDao.searchOne(init);
//		init = initService.selectByWhere(init);
		
		if (null != init) {
			String registcode = init.getInitvalue();
			
			//如果注册码为空
			if (!init.getInitvalue().equals("") && createRegistCode.equals(registcode)) {
				result = true;
			}
		}
				
		return result;
	}

	@Override
	public String getLocalMacAddress() {
		String os = SystemTool.getOSName();
		
		String macAddress = "";
		if (os.startsWith("windows")) {
			macAddress = SystemTool.getWindowsMACAddress();
			
		} else if (os.startsWith("linux")) {
			macAddress = SystemTool.getLinuxMACAddress();
		} else {
			macAddress = SystemTool.getMacosMACAddress();
		}
		return macAddress;
	}

	@Override
	public String getRegistCode(String str) throws Exception {
		EncryptionDecryption des = new EncryptionDecryption("regist");
		
		String registCode = des.encrypt(str);
		return registCode;
	}

	@Override
	public Sys_account login(Sys_account account) {
		Sys_account tmp = new Sys_account();
		tmp.setAccountcode(account.getAccountcode());
		Sys_account result = accountDao.searchOne(tmp);
		if (result == null ) {
			return null;
		}
		// 将输入的密码与Pojo里的密码MD5后对比，如果不匹配，说明密码不对
		if (!MD5.encode(account.getPassword()).equals(
				result.getPassword())) {
			return null;
		}
		return result;
	}

}
