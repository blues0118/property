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
	private String leasename;
	private String leasetype;
	private String leasenumber;
	private String phonenumber;
	private String mobilephone;
	private String leasefilename;
	
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
	public String getLeasename() {
		return leasename;
	}
	public void setLeasename(String leasename) {
		this.leasename = leasename;
	}
	public String getLeasetype() {
		return leasetype;
	}
	public void setLeasetype(String leasetype) {
		this.leasetype = leasetype;
	}
	public String getLeasenumber() {
		return leasenumber;
	}
	public void setLeasenumber(String leasenumber) {
		this.leasenumber = leasenumber;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getLeasefilename() {
		return leasefilename;
	}
	public void setLeasefilename(String leasefilename) {
		this.leasefilename = leasefilename;
	}
	
	
	
}
