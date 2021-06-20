package com.ddlscript.def.models.projects.scripts;

import com.ddlscript.sdk.Model;
import lombok.Getter;

/**
 * Possible states a script can be in.
 */
public enum ScriptState implements Model {

	/**
	 * Indicates that the script is currently being worked on, aka in a drafting state.
	 */
	DRAFT(1, true),

	/**
	 * Indicates that the script is ready for pere review.
	 */
	PENDING_REVIEW(2, true),

	/**
	 * Indicates that the script has been merged into the mainline.
	 */
	MERGED(3, false),

	/**
	 * Indicates that the script's recent review was declined.
	 */
	DECLINED(4, true),

	/**
	 * Indicates that the script has been deleted and removed from the system. Ideally only administrator will be able
	 * to see script that are in this state.
	 */
	DELETED(5, false);

	public static ScriptState ofIdentifier(final int withValue) {
		for (ScriptState permission : ScriptState.values()) {
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
	 * Flag to indicate if the state is considered to be OPEN or CLOSED.
	 */
	@Getter
	private final boolean open;

	/**
	 * Default constructor.
	 *
	 * @param withDatabaseValue
	 * 		database value.
	 */
	ScriptState(final int withDatabaseValue, final boolean isOpen) {
		this.databaseValue = withDatabaseValue;
		this.open = isOpen;
	}
}
