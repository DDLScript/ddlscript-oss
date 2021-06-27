package com.ddlscript.def.permissions.system;

import com.ddlscript.sdk.Model;
import lombok.Getter;

/**
 * System based permissions.
 */
public enum SystemPermission implements Model {

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
	MANAGE_API_KEYS(4),

	/**
	 * Grants the user the ability to delete projects they have access to.
	 */
	DELETE_PROJECTS(5);

	public static SystemPermission ofIdentifier(final int withValue) {
		for (SystemPermission permission : SystemPermission.values()) {
			if (permission.getDatabaseValue() == withValue) {
				return permission;
			}
		}

		throw new IllegalArgumentException("Value " + withValue + " is not valid.");
	}

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
