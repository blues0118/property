package net.ussoft.property.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.ussoft.property.dao.EquipmentDao;
import net.ussoft.property.model.Equipment;
import net.ussoft.property.model.PageBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("equipmentDao")
public class EquipmentDaoImpl extends BaseDaoMysqlImpl<Equipment, String> implements EquipmentDao {

	public EquipmentDaoImpl() {
		super(Equipment.class);
	}
	@Override
	public PageBean<Equipment> search(String sql,List<Object> values,PageBean<Equipment> pageBean){
		if(StringUtils.isEmpty(sql)){
			List<Equipment> reList=new ArrayList<Equipment>();
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
		List<Equipment> list = this.search(sb.toString(), values);
		pageBean.setList(list);
		pageBean.setRowCount(list.size());
		return pageBean;
	}
}
