package com.ddlscript.def.usergroups.members;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.usergroups.UserGroupIdentifier;
import com.ddlscript.def.users.UserIdentifier;

/**
 * Represents a single user assigned to a user group.
 */
public interface UserGroupMemberModel extends AuditedModel {

	/**
	 * Returns the identifier of the user group the user is linked to.
	 *
	 * @return user group Identifier.
	 */
	UserGroupIdentifier getUserGroupIdentifier();

	/**
	 * Returns the identifier of the user being assigned to the user group
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

}
