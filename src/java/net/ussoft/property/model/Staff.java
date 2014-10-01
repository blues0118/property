package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="staff")
public class Staff {

	private String id;
	private String staffcode;
	private String staffmemo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStaffcode() {
		return staffcode;
	}
	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	public String getStaffmemo() {
		return staffmemo;
	}
	public void setStaffmemo(String staffmemo) {
		this.staffmemo = staffmemo;
	}
	
	
}
