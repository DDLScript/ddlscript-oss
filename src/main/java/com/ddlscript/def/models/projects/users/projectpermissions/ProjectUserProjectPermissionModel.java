package com.ddlscript.def.models.projects.users.projectpermissions;

import com.ddlscript.def.models.permissions.ProjectPermission;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single project permission that has been granted to a user for a specific project.
 */
public interface ProjectUserProjectPermissionModel {

	/**
	 * Returns the timestamp of when the permission was granted to the user.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

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

	/**
	 * Returns the identifier of the user whom granted the permission.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
