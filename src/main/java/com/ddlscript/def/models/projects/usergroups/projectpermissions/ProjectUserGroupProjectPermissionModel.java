package com.ddlscript.def.models.projects.usergroups.projectpermissions;

import com.ddlscript.def.models.permissions.ProjectPermission;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single System permission that has been granted to a user group for a specified project.
 */
public interface ProjectUserGroupProjectPermissionModel {

	/**
	 * Returns the timestamp of when the permission was granted to the user group.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

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

	/**
	 * Returns the identifier of the user whom granted the permission.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
