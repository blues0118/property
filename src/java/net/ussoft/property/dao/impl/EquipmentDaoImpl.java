package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.EquipmentDao;
import net.ussoft.property.model.Equipment;

import org.springframework.stereotype.Repository;

@Repository("equipmentDao")
public class EquipmentDaoImpl extends BaseDaoMysqlImpl<Equipment, String> implements EquipmentDao {

	public EquipmentDaoImpl() {
		super(Equipment.class);
	}
}
