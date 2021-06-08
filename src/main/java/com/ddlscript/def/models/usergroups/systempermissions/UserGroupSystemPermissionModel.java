package com.ddlscript.def.models.usergroups.systempermissions;

import com.ddlscript.def.models.permissions.SystemPermission;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single System permission that has been granted to a user group.
 */
public interface UserGroupSystemPermissionModel {

	/**
	 * Returns the timestamp of when the permission was granted to the user group.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the identifier of the user group the permission has been granted to.
	 *
	 * @return User Identifier.
	 */
	UserGroupIdentifier getUserGroupIdentifier();

	/**
	 * Returns the system permission being granted to the user group.
	 *
	 * @return system permission
	 */
	SystemPermission getSystemPermission();

	/**
	 * Returns the identifier of the user whom granted the permission.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
