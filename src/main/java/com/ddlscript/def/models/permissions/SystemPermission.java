package com.ddlscript.def.models.permissions;

import lombok.Getter;

/**
 * System based permissions.
 */
public enum SystemPermission {

	/**
	 * Grants the user the ability to create new projects.
	 */
	CREATE_PROJECTS(1),

	/**
	 * Grants the user the ability to manage users.
	 */
	MANAGE_USERS(2),

	/**
	 * Grants the user the ability to manage user groups.
	 */
	MANAGE_USER_GROUPS(3),

	/**
	 * Grants the user the ability to manage API keys.
	 */
	MANAGE_API_KEYS(4);

	/**
	 * Database value that represents this setting.
	 */
	@Getter
	private final int databaseValue;

	/**
	 * Default constructor.
	 *
	 * @param withDatabaseValue
	 * 		database value.
	 */
	SystemPermission(final int withDatabaseValue) {
		this.databaseValue = withDatabaseValue;
	}
}
