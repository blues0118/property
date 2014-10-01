package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="equipmentcontent")
public class Equipmentcontent {

	private String id;
	private String equipmentid;
	private String eqcontent;
	private Integer eqsum;
	private String eqdate;
	private String termid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	public String getEqcontent() {
		return eqcontent;
	}
	public void setEqcontent(String eqcontent) {
		this.eqcontent = eqcontent;
	}
	public Integer getEqsum() {
		return eqsum;
	}
	public void setEqsum(Integer eqsum) {
		this.eqsum = eqsum;
	}
	public String getEqdate() {
		return eqdate;
	}
	public void setEqdate(String eqdate) {
		this.eqdate = eqdate;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	
	
}
