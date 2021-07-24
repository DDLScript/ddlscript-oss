package com.ddlscript.def.common;

import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.sdk.Model;

/**
 * Represents a model with Auditing details.
 */
public interface AuditedModel extends Model, TimestampCreatedModel {

	/**
	 * Returns the identifier of the user whom created the instance.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
