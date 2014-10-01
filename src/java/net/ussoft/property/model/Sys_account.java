package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_account")
public class Sys_account {

	private String id;
	private String accountcode;
	private String password;
	private String accountname;
	private Integer accountstate;
	private String accountmemo;
	private String projectid;
	private String roleid;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccountcode() {
		return accountcode;
	}
	
	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}
	public Integer getAccountstate() {
		return accountstate;
	}
	public void setAccountstate(Integer accountstate) {
		this.accountstate = accountstate;
	}
	public String getAccountmemo() {
		return accountmemo;
	}
	public void setAccountmemo(String accountmemo) {
		this.accountmemo = accountmemo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	
	
}
