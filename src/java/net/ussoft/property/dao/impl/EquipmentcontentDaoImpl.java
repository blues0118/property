package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.EquipmentcontentDao;
import net.ussoft.property.model.Equipmentcontent;

import org.springframework.stereotype.Repository;

@Repository("equipmentcontentDao")
public class EquipmentcontentDaoImpl extends BaseDaoMysqlImpl<Equipmentcontent, String> implements EquipmentcontentDao {

	public EquipmentcontentDaoImpl() {
		super(Equipmentcontent.class);
	}
}
