package com.ddlscript.def.usergroups;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.sdk.models.IdentifiableModel;

/**
 * Represents a single user group within the system.
 */
public interface UserGroupModel extends IdentifiableModel<UserGroupIdentifier>, AuditedModel {

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_NAME_LENGTH = 100;

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_DESCRIPTION_LENGTH = 10000;

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
