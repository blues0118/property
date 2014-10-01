package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="otherpay")
public class Otherpay {

	private String id;
	private String paydate;
	private String paymemo;
	private Integer paysum;
	private String termid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getPaymemo() {
		return paymemo;
	}
	public void setPaymemo(String paymemo) {
		this.paymemo = paymemo;
	}
	public Integer getPaysum() {
		return paysum;
	}
	public void setPaysum(Integer paysum) {
		this.paysum = paysum;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	
	
	
}
