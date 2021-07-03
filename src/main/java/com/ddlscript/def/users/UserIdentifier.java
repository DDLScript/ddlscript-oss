package com.ddlscript.def.users;

import com.ddlscript.def.AbstractPositiveIdentifier;

/**
 * Represents the identifier of a User.
 */
public class UserIdentifier extends AbstractPositiveIdentifier {

	/**
	 * Default constructor that accepts a raw identifier value.
	 *
	 * @param withRawValue Raw Identifier
	 */
	public UserIdentifier(final int withRawValue) {
		super(withRawValue);
	}
}
