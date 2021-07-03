package com.ddlscript.def.usergroups.members;

import com.ddlscript.def.usergroups.UserGroupIdentifier;
import com.ddlscript.def.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single user assigned to a user group.
 */
public interface UserGroupMemberModel {

	/**
	 * Returns the timestamp of when the user was added to the user group.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

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

	/**
	 * Returns the identifier of the user whom linked the user to the user group
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
