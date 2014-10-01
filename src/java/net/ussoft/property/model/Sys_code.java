package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_code")
public class Sys_code {

	private String id;
	private String fieldcode;
	private String fieldcncode;
	private String fieldkey;
	private String fieldvalue;
	private Integer sort;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFieldcode() {
		return fieldcode;
	}
	public void setFieldcode(String fieldcode) {
		this.fieldcode = fieldcode;
	}
	public String getFieldkey() {
		return fieldkey;
	}
	public void setFieldkey(String fieldkey) {
		this.fieldkey = fieldkey;
	}
	public String getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getFieldcncode() {
		return fieldcncode;
	}
	public void setFieldcncode(String fieldcncode) {
		this.fieldcncode = fieldcncode;
	}
	
}
