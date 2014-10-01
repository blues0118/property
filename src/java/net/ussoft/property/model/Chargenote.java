package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="chargenote")
public class Chargenote {

	private String id;
	private String chargedate;
	private String chargeman;
	private String accountid;
	private String barcode;
	private Integer chargestatus;
	private String unitid;
	private String invoicenumber;
	private String jbr;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChargedate() {
		return chargedate;
	}
	public void setChargedate(String chargedate) {
		this.chargedate = chargedate;
	}
	public String getChargeman() {
		return chargeman;
	}
	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getChargestatus() {
		return chargestatus;
	}
	public void setChargestatus(Integer chargestatus) {
		this.chargestatus = chargestatus;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getInvoicenumber() {
		return invoicenumber;
	}
	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}
	public String getJbr() {
		return jbr;
	}
	public void setJbr(String jbr) {
		this.jbr = jbr;
	}
	
	
	
}
