package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="watchchange")
public class Watchchange {
	
	private String id;
	private String watchcode;
	private String itemid;
	private Integer watchnewnumber;
	private Integer watchnumber;
	private Integer watcholdnumber;
	private String projectid;
	private String unitid;
	private String changetime;
	private String changeman;
	private String changemanid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWatchcode() {
		return watchcode;
	}
	public void setWatchcode(String watchcode) {
		this.watchcode = watchcode;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public Integer getWatchnewnumber() {
		return watchnewnumber;
	}
	public void setWatchnewnumber(Integer watchnewnumber) {
		this.watchnewnumber = watchnewnumber;
	}
	public Integer getWatchnumber() {
		return watchnumber;
	}
	public void setWatchnumber(Integer watchnumber) {
		this.watchnumber = watchnumber;
	}
	public Integer getWatcholdnumber() {
		return watcholdnumber;
	}
	public void setWatcholdnumber(Integer watcholdnumber) {
		this.watcholdnumber = watcholdnumber;
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
	public String getChangetime() {
		return changetime;
	}
	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}
	public String getChangeman() {
		return changeman;
	}
	public void setChangeman(String changeman) {
		this.changeman = changeman;
	}
	public String getChangemanid() {
		return changemanid;
	}
	public void setChangemanid(String changemanid) {
		this.changemanid = changemanid;
	}
	

	
}
