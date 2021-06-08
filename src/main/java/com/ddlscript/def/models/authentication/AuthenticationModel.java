package com.ddlscript.def.models.authentication;

import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.sdk.Model;

/**
 * Represents authentication.
 */
public interface AuthenticationModel extends Model {

	/**
	 * Returns the identifier of the user whom the session is for.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

	/**
	 * Returns whether the provided password is valid.
	 *
	 * @param withPassword Password to test
	 * @return {@code true} if the provided password is valid, {@code false} otherwise.
	 */
	boolean isValidPassword(final String withPassword);
}
