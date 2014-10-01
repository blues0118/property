package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="staffcontent")
public class Staffcontent {

	private String id;
	private String termid;
	private Integer wage;
	private Integer safe;
	private String staffid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public Integer getWage() {
		return wage;
	}
	public void setWage(Integer wage) {
		this.wage = wage;
	}
	public Integer getSafe() {
		return safe;
	}
	public void setSafe(Integer safe) {
		this.safe = safe;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	
	
	
}
