package net.ussoft.property.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.ussoft.property.dao.StaffcontentDao;
import net.ussoft.property.model.PageBean;
import net.ussoft.property.model.Staffcontent;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("staffcontentDao")
public class StaffcontentDaoImpl extends BaseDaoMysqlImpl<Staffcontent, String> implements StaffcontentDao {

	public StaffcontentDaoImpl() {
		super(Staffcontent.class);
	}
	@Override
	public PageBean<Staffcontent> search(String sql,List<Object> values,PageBean<Staffcontent> pageBean){
		if(StringUtils.isEmpty(sql)){
			List<Staffcontent> reList=new ArrayList<Staffcontent>();
			pageBean.setRowCount(0);
			pageBean.setList(reList);
			return pageBean;
		}
		if(values==null)values=new ArrayList<Object>();

		int startRow=pageBean.getStartRow();
		int offset=pageBean.getPageSize();
		StringBuilder sb=new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(pageBean.getGroupby())){
			sb.append(" group by ");
			sb.append(pageBean.getGroupby());
		}
		
		//int count=this.getCount(sb.toString(), values);
		//pageBean.setRowCount(count);
		
		if(!StringUtils.isEmpty(pageBean.getOrderBy())){
			sb.append(" order by ");
			sb.append(pageBean.getOrderBy());
			if(!StringUtils.isEmpty(pageBean.getOrderType())){
				sb.append(" ");
				sb.append(pageBean.getOrderType());
			}
		}
		if (pageBean.getIsPage()) {
			sb.append(" limit ");
			sb.append(startRow);
			sb.append(",");
			sb.append(offset);
		}
		List<Staffcontent> list = this.search(sb.toString(), values);
		pageBean.setList(list);
		pageBean.setRowCount(list.size());
		return pageBean;
	}
}
