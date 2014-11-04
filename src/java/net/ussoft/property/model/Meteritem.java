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
	private String chargeitemid;
	private Integer lastnumber;
	private Integer newnumber;
	private String watchcode;
	private String meterman;
	private String createtime;
	
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
	public String getChargeitemid() {
		return chargeitemid;
	}
	public void setChargeitemid(String chargeitemid) {
		this.chargeitemid = chargeitemid;
	}
	public String getMeterman() {
		return meterman;
	}
	public void setMeterman(String meterman) {
		this.meterman = meterman;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
