package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="bookterm")
public class Bookterm {

	private String id;
	private String termcode;
	private String termmemo;
	private String projectid;
	private String createtime;
	private String createman;
	private Integer tremstatus;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTermcode() {
		return termcode;
	}
	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}
	public String getTermmemo() {
		return termmemo;
	}
	public void setTermmemo(String termmemo) {
		this.termmemo = termmemo;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreateman() {
		return createman;
	}
	public void setCreateman(String createman) {
		this.createman = createman;
	}
	public Integer getTremstatus() {
		return tremstatus;
	}
	public void setTremstatus(Integer tremstatus) {
		this.tremstatus = tremstatus;
	}
	
	
	
	
}
