package com.ddlscript.def.users.systempermissions;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.users.UserIdentifier;

/**
 * Represents a single System permission that has been granted to a user..
 */
public interface UserSystemPermissionModel extends AuditedModel {

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
}
