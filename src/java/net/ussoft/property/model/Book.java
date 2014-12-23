package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="book")
public class Book {

	private String id;
	private String projectid;
	private String unitid;
	private String chargetime;
	private String chargeovertime;
	private String itemid;
	private String itemcode;
	private Integer iswatch;
	private Integer lastnumber;
	private Integer newnumber;
	private Integer chargestatus;
	private Integer chargesum;
	private String noteid;
	private String termid;
	private String unittermid;
	private String bookmemo;
	private String chargeprice;
	
	
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
	public String getChargetime() {
		return chargetime;
	}
	public void setChargetime(String chargetime) {
		this.chargetime = chargetime;
	}
	public String getChargeovertime() {
		return chargeovertime;
	}
	public void setChargeovertime(String chargeovertime) {
		this.chargeovertime = chargeovertime;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public Integer getIswatch() {
		return iswatch;
	}
	public void setIswatch(Integer iswatch) {
		this.iswatch = iswatch;
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
	public Integer getChargestatus() {
		return chargestatus;
	}
	public void setChargestatus(Integer chargestatus) {
		this.chargestatus = chargestatus;
	}
	public Integer getChargesum() {
		return chargesum;
	}
	public void setChargesum(Integer chargesum) {
		this.chargesum = chargesum;
	}
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getUnittermid() {
		return unittermid;
	}
	public void setUnittermid(String unittermid) {
		this.unittermid = unittermid;
	}
	public String getBookmemo() {
		return bookmemo;
	}
	public void setBookmemo(String bookmemo) {
		this.bookmemo = bookmemo;
	}
	public String getChargeprice() {
		return chargeprice;
	}
	public void setChargeprice(String chargeprice) {
		this.chargeprice = chargeprice;
	}
	
	
}
