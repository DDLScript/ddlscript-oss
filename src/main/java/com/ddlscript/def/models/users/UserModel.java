package com.ddlscript.def.models.users;

import com.ddlscript.sdk.models.IdentifiableModel;

import java.time.Instant;

/**
 * Represents a single user within the system.
 */
public interface UserModel extends IdentifiableModel<UserIdentifier> {

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_NAME_LENGTH = 100;

	/**
	 * Maximum number of characters allowed in the USERNAME field.
	 */
	int MAX_USERNAME_LENGTH = 100;

	/**
	 * Maximum number of characters allowed in the EMAIL field.
	 */
	int MAX_EMAIL_LENGTH = 500;

	/**
	 * Returns the timestamp of when the user was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the timestamp of when the user was archived.
	 *
	 * @return Archived timestamp.
	 */
	Instant getTimestampArchived();

	/**
	 * Returns the name of the User.
	 *
	 * @return Name of the user.
	 */
	String getName();

	/**
	 * Returns the username of the User.
	 *
	 * @return UserName of the user.
	 */
	String getUsername();

	/**
	 * Returns the email address of the User.
	 *
	 * @return Email Address of the user.
	 */
	String getEmail();

	/**
	 * Returns whether the user has been granted administrator access.
	 *
	 * @return {@code true} if the user is an administrator, {@code false} otherwise.
	 */
	boolean isAdministrator();

	/**
	 * Returns whether the user has been archived.
	 *
	 * @return {@code true} if the user is archived, {@code false} otherwise.
	 */
	default boolean isArchived() {
		return this.getTimestampArchived() != null;
	}

	/**
	 * Returns whether the given password matches the one we have stored for this user.
	 *
	 * @param withPassword
	 * 		Password to check
	 * @return {@code true} if the passwords match, {@code false} otherwise.
	 */
	boolean verifyPassword(final String withPassword);
}
