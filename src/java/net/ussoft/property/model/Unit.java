package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="unit")
public class Unit {

	private String id;
	private String unitcode;
	private Float building_area;
	private Float using_area;
	private String unitstate;
	private String unittype;
	private Integer ischarge;
	private String leaseid;
	private String projeuctid;
	private Integer unitsort;
	private Integer unitrowsort;
	private String unitcolor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public Float getBuilding_area() {
		return building_area;
	}
	public void setBuilding_area(Float building_area) {
		this.building_area = building_area;
	}
	public Float getUsing_area() {
		return using_area;
	}
	public void setUsing_area(Float using_area) {
		this.using_area = using_area;
	}
	public String getUnitstate() {
		return unitstate;
	}
	public void setUnitstate(String unitstate) {
		this.unitstate = unitstate;
	}
	public String getUnittype() {
		return unittype;
	}
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	public Integer getIscharge() {
		return ischarge;
	}
	public void setIscharge(Integer ischarge) {
		this.ischarge = ischarge;
	}
	public String getLeaseid() {
		return leaseid;
	}
	public void setLeaseid(String leaseid) {
		this.leaseid = leaseid;
	}
	public String getProjeuctid() {
		return projeuctid;
	}
	public void setProjeuctid(String projeuctid) {
		this.projeuctid = projeuctid;
	}
	public Integer getUnitsort() {
		return unitsort;
	}
	public void setUnitsort(Integer unitsort) {
		this.unitsort = unitsort;
	}
	public Integer getUnitrowsort() {
		return unitrowsort;
	}
	public void setUnitrowsort(Integer unitrowsort) {
		this.unitrowsort = unitrowsort;
	}
	public String getUnitcolor() {
		return unitcolor;
	}
	public void setUnitcolor(String unitcolor) {
		this.unitcolor = unitcolor;
	}
	
	
}
