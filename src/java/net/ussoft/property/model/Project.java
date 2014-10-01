package net.ussoft.property.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="project")
public class Project {
	
	private String id;
	private String projectname;
	private String projecttype;
	private String projectmemo;
	private Integer sort;
	private String parentid;
	private Integer nodeindex;
	private String treenode;
	private Float building_area;
	private String projectpic;
	private String projectcolor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	public String getProjectmemo() {
		return projectmemo;
	}
	public void setProjectmemo(String projectmemo) {
		this.projectmemo = projectmemo;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public Integer getNodeindex() {
		return nodeindex;
	}
	public void setNodeindex(Integer nodeindex) {
		this.nodeindex = nodeindex;
	}
	public String getTreenode() {
		return treenode;
	}
	public void setTreenode(String treenode) {
		this.treenode = treenode;
	}
	public Float getBuilding_area() {
		return building_area;
	}
	public void setBuilding_area(Float building_area) {
		this.building_area = building_area;
	}
	public String getProjectpic() {
		return projectpic;
	}
	public void setProjectpic(String projectpic) {
		this.projectpic = projectpic;
	}
	public String getProjectcolor() {
		return projectcolor;
	}
	public void setProjectcolor(String projectcolor) {
		this.projectcolor = projectcolor;
	}
	
	
}
