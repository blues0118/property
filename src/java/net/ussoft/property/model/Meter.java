package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="meter")
public class Meter {

	
	private String id;
	private String meterdate;
	private String projectid;
	private String accountid;
	private String accountname;
	private String meterman;
	private String createtime;
	private String metermemo;
	private Integer meterstatus;
	private String termid;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeterdate() {
		return meterdate;
	}
	public void setMeterdate(String meterdate) {
		this.meterdate = meterdate;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
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
	public String getMetermemo() {
		return metermemo;
	}
	public void setMetermemo(String metermemo) {
		this.metermemo = metermemo;
	}
	public Integer getMeterstatus() {
		return meterstatus;
	}
	public void setMeterstatus(Integer meterstatus) {
		this.meterstatus = meterstatus;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	
}
