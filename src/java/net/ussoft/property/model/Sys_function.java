package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_function")
public class Sys_function {

	private String id;
	private String funcode;
	private String funencode;
	private String funpath;
	private Integer funsort;
	private Integer funparent;
	private String funtype;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFuncode() {
		return funcode;
	}
	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}
	public String getFunencode() {
		return funencode;
	}
	public void setFunencode(String funencode) {
		this.funencode = funencode;
	}
	public String getFunpath() {
		return funpath;
	}
	public void setFunpath(String funpath) {
		this.funpath = funpath;
	}
	public Integer getFunsort() {
		return funsort;
	}
	public void setFunsort(Integer funsort) {
		this.funsort = funsort;
	}
	public Integer getFunparent() {
		return funparent;
	}
	public void setFunparent(Integer funparent) {
		this.funparent = funparent;
	}
	public String getFuntype() {
		return funtype;
	}
	public void setFuntype(String funtype) {
		this.funtype = funtype;
	}
	
}
