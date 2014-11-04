package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="chargeitem")
public class Chargeitem {

	private String id;
	private String itemcode;
	private String chargeremark;
	private String itemcontent;
	private Integer iswatch;
	private Integer watchtype;
	private Integer itemsort;
	private String projectid;
	private String unitid;
	private Integer watchnumber;
	private String begindate;
	private String reminddate;
	private String nextdate;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getChargeremark() {
		return chargeremark;
	}
	public void setChargeremark(String chargeremark) {
		this.chargeremark = chargeremark;
	}
	public String getItemcontent() {
		return itemcontent;
	}
	public void setItemcontent(String itemcontent) {
		this.itemcontent = itemcontent;
	}
	public Integer getIswatch() {
		return iswatch;
	}
	public void setIswatch(Integer iswatch) {
		this.iswatch = iswatch;
	}
	public Integer getWatchtype() {
		return watchtype;
	}
	public void setWatchtype(Integer watchtype) {
		this.watchtype = watchtype;
	}
	public Integer getItemsort() {
		return itemsort;
	}
	public void setItemsort(Integer itemsort) {
		this.itemsort = itemsort;
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
	public Integer getWatchnumber() {
		return watchnumber;
	}
	public void setWatchnumber(Integer watchnumber) {
		this.watchnumber = watchnumber;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getReminddate() {
		return reminddate;
	}
	public void setReminddate(String reminddate) {
		this.reminddate = reminddate;
	}
	public String getNextdate() {
		return nextdate;
	}
	public void setNextdate(String nextdate) {
		this.nextdate = nextdate;
	}
	
	
}
