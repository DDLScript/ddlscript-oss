package com.ddlscript.def.users.systempermissions;

import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single System permission that has been granted to a user..
 */
public interface UserSystemPermissionModel {

	/**
	 * Returns the timestamp of when the permission was granted to the user.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the identifier of the user the permission has been granted to.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

	/**
	 * Returns the system permission being granted to the user.
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
