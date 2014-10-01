package net.ussoft.property.dao.impl;

import net.ussoft.property.dao.ProjectDao;
import net.ussoft.property.model.Project;

import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoMysqlImpl<Project, String> implements ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}
}
