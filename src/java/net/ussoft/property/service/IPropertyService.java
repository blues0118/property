package net.ussoft.property.service;

import java.util.List;
import java.util.Map;

/*
 * wangzibo
 * 单元管理的相关操作
 */
public interface IPropertyService {

	/*
	 * 收款通知单批量打印功能->到期固定收费项目查询
	 */
	public List<Map<String,Object>> queryChargeitemForMap(String projectid,String unitids);
	/*
	 * 收款通知单批量打印功能->到期抄表收费项目查询
	 */
	public List<Map<String, Object>> queryBookForMap(String projectid,String unitids);
	/*
	 * 收款通知单批量打印功能->公共信息查询
	 */
	public List<Map<String, Object>> queryCommonForMap(String projectid,String unitids);
	
}
