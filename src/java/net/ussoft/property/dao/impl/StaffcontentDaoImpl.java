package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.StaffcontentDao;
import net.ussoft.property.model.Staffcontent;

import org.springframework.stereotype.Repository;

@Repository("staffcontentDao")
public class StaffcontentDaoImpl extends BaseDaoMysqlImpl<Staffcontent, String> implements StaffcontentDao {

	public StaffcontentDaoImpl() {
		super(Staffcontent.class);
	}
}
