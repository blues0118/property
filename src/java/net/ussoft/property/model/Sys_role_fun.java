package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_role_fun")
public class Sys_role_fun {
	
	private String id;
	private String roleid;
	private String funid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getFunid() {
		return funid;
	}
	public void setFunid(String funid) {
		this.funid = funid;
	}
	

}
