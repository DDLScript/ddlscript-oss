package com.ddlscript.def.projects.users.projectpermissions;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.users.UserIdentifier;

/**
 * Represents a single project permission that has been granted to a user for a specific project.
 */
public interface ProjectUserProjectPermissionModel extends AuditedModel {

	/**
	 * Returns the identifier of the project the user has been granted a permission for.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user the permission has been granted to.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

	/**
	 * Returns the project permission being granted to the user.
	 *
	 * @return project permission
	 */
	ProjectPermission getProjectPermission();

}
