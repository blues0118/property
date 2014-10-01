package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="meteritem")
public class Meteritem {

	private String id;
	private String projectid;
	private String unitid;
	private String watchid;
	private Integer lastnumber;
	private Integer newnumber;
	private String watchcode;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getWatchid() {
		return watchid;
	}
	public void setWatchid(String watchid) {
		this.watchid = watchid;
	}
	public Integer getLastnumber() {
		return lastnumber;
	}
	public void setLastnumber(Integer lastnumber) {
		this.lastnumber = lastnumber;
	}
	public Integer getNewnumber() {
		return newnumber;
	}
	public void setNewnumber(Integer newnumber) {
		this.newnumber = newnumber;
	}
	public String getWatchcode() {
		return watchcode;
	}
	public void setWatchcode(String watchcode) {
		this.watchcode = watchcode;
	}
	
	
	
}
