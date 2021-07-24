package com.ddlscript.def.projects.usergroups;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.usergroups.UserGroupIdentifier;

/**
 * Represents a single user group assigned to a project.
 */
public interface ProjectUserGroupModel extends AuditedModel {

	/**
	 * Returns the identifier of the project the user group is linked to.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user group being assigned to the project
	 *
	 * @return User Group Identifier.
	 */
	UserGroupIdentifier getUserGroupIdentifier();

}
