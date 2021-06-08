package com.ddlscript.def.models.usergroups;

import com.ddlscript.def.models.AbstractPositiveIdentifier;
import lombok.NonNull;

/**
 * Represents the identifier of a User Group.
 */
public class UserGroupIdentifier extends AbstractPositiveIdentifier {

	/**
	 * Default constructor that accepts a raw identifier value.
	 *
	 * @param withRawValue Raw Identifier
	 */
	public UserGroupIdentifier(@NonNull final Integer withRawValue) {
		super(withRawValue);
	}
}
