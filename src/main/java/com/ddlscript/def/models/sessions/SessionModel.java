package com.ddlscript.def.models.sessions;

import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.sdk.Model;

import java.time.Instant;

/**
 * Represents a single user session.
 */
public interface SessionModel extends Model {

	/**
	 * Returns the token identifying this session.
	 *
	 * @return Session Token.
	 */
	String getToken();

	/**
	 * Returns the identifier of the user whom the session is for.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

	/**
	 * Returns the timestamp of when the session was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();
}
