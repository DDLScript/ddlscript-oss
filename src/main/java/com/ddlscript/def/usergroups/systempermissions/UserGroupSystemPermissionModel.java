package com.ddlscript.def.usergroups.systempermissions;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.usergroups.UserGroupIdentifier;

/**
 * Represents a single System permission that has been granted to a user group.
 */
public interface UserGroupSystemPermissionModel extends AuditedModel {

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

}
