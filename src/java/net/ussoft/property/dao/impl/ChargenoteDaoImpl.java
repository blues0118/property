package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.ChargenoteDao;
import net.ussoft.property.model.Chargenote;

import org.springframework.stereotype.Repository;

@Repository("chargenoteDao")
public class ChargenoteDaoImpl extends BaseDaoMysqlImpl<Chargenote, String> implements ChargenoteDao {

	public ChargenoteDaoImpl() {
		super(Chargenote.class);
	}
}
