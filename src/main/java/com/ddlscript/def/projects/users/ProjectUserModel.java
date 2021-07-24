package com.ddlscript.def.projects.users;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.users.UserIdentifier;

/**
 * Represents a single user assigned to a project.
 */
public interface ProjectUserModel extends AuditedModel {

	/**
	 * Returns the identifier of the project the user is linked to.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user being assigned to the project
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

}
