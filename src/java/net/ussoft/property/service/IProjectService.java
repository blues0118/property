package net.ussoft.property.service;

import java.util.List;

import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Project;

/**
 * 物业项目管理service
 * @author wangf
 *
 */
public interface IProjectService {

	/**
	 * 根据id，获取对象
	 * @param id
	 * @return
	 */
	public Project getById(String id);
	
	/**
	 * 获取全部信息
	 * @return
	 */
	public List<Project> list();
	
	/**
	 * 分页读取信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<Project> list(Project t,PageBean<Project> pageBean);
	
	/**
	 * 根据条件，获取全部信息
	 * @return
	 */
	public List<Project> list(Project project);
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	public int update(Project project);
	
	/**
	 * 插入新记录
	 * @param docserver
	 * @return 
	 */
	public Project insert(Project project);
	
	/**
	 * 根据条件获取数据
	 * @param role
	 * @return
	 */
	public Project getByWhere(Project project);
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
    /**
     * 获取用户访问的物业项目
     * @param account_id
     * @return
     */
    public List<Project> list(String account_id);
}
