package com.ddlscript.def.models.usergroups;

import com.ddlscript.sdk.models.IdentifiableModel;
import com.ddlscript.def.models.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single user group within the system.
 */
public interface UserGroupModel extends IdentifiableModel<UserGroupIdentifier> {

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_NAME_LENGTH = 100;

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_DESCRIPTION_LENGTH = 10000;

	/**
	 * Returns the identifier of the user whom created the user group.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();

	/**
	 * Returns the timestamp of when the user group was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the name of the User Group.
	 *
	 * @return Name of the user group.
	 */
	String getName();

	/**
	 * Returns the description associated with the User Group.
	 *
	 * @return Description associated with the user group.
	 */
	String getDescription();

}
