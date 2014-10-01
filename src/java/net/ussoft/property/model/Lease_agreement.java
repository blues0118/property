package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="lease_agreement")
public class Lease_agreement {

	private String id;
	private String leasecode;
	private String beingdate;
	private String enddate;
	private String unitid;
	private String leaseid;
	private String leasefile;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLeasecode() {
		return leasecode;
	}
	public void setLeasecode(String leasecode) {
		this.leasecode = leasecode;
	}
	public String getBeingdate() {
		return beingdate;
	}
	public void setBeingdate(String beingdate) {
		this.beingdate = beingdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getLeaseid() {
		return leaseid;
	}
	public void setLeaseid(String leaseid) {
		this.leaseid = leaseid;
	}
	public String getLeasefile() {
		return leasefile;
	}
	public void setLeasefile(String leasefile) {
		this.leasefile = leasefile;
	}
	
	
	
}
