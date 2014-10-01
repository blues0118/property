package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="unitterm")
public class Unitterm {

	private String id;
	private String unittermcode;
	private String termid;
	private String unittermmemo;
	private String projectid;
	private String unitid;
	private Integer unittermstatus;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnittermcode() {
		return unittermcode;
	}
	public void setUnittermcode(String unittermcode) {
		this.unittermcode = unittermcode;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getUnittermmemo() {
		return unittermmemo;
	}
	public void setUnittermmemo(String unittermmemo) {
		this.unittermmemo = unittermmemo;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public Integer getUnittermstatus() {
		return unittermstatus;
	}
	public void setUnittermstatus(Integer unittermstatus) {
		this.unittermstatus = unittermstatus;
	}
	
	
	
}
