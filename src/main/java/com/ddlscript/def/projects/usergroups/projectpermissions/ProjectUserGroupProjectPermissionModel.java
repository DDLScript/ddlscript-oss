package com.ddlscript.def.projects.usergroups.projectpermissions;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.usergroups.UserGroupIdentifier;

/**
 * Represents a single System permission that has been granted to a user group for a specified project.
 */
public interface ProjectUserGroupProjectPermissionModel extends AuditedModel {

	/**
	 * Returns the identifier of the project the user group has been granted a permission for.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user group the permission has been granted to.
	 *
	 * @return User Identifier.
	 */
	UserGroupIdentifier getUserGroupIdentifier();

	/**
	 * Returns the project permission being granted to the user group.
	 *
	 * @return project permission
	 */
	ProjectPermission getProjectPermission();
}
