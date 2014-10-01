package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="lease")
public class Lease {

	private String id;
	private String leasename;
	private String leaseunit;
	private String leasetype;
	private String leasenumber;
	private String phonenumber;
	private String mobilephone;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLeasename() {
		return leasename;
	}
	public void setLeasename(String leasename) {
		this.leasename = leasename;
	}
	public String getLeaseunit() {
		return leaseunit;
	}
	public void setLeaseunit(String leaseunit) {
		this.leaseunit = leaseunit;
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
	
	
}
