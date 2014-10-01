package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.StaffDao;
import net.ussoft.property.model.Staff;

import org.springframework.stereotype.Repository;

@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoMysqlImpl<Staff, String> implements StaffDao {

	public StaffDaoImpl() {
		super(Staff.class);
	}
}
