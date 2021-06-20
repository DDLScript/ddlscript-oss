package com.ddlscript.def.models.projects.scripts;

import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.sdk.models.IdentifiableModel;

import java.time.Instant;

/**
 * Represents a single project within the system.
 */
public interface ScriptModel extends IdentifiableModel<ScriptIdentifier> {

	/**
	 * Maximum number of characters allowed in the TITLE field.
	 */
	int MAX_TITLE_LENGTH = 300;

	/**
	 * Returns the timestamp of when the project was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the timestamp of when the project was lst updated.
	 *
	 * @return Updated timestamp.
	 */
	Instant getTimestampUpdated();

	/**
	 * Returns the identifier of the project the script is part of.
	 *
	 * @return Project Identifier
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the title of the script.
	 *
	 * @return Title the script.
	 */
	String getTitle();

	/**
	 * Returns the description of the script.
	 *
	 * @return Description the script.
	 */
	String getDescription();

	/**
	 * Returns the identifier of the user whom created the project.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();

	/**
	 * Returns the identifier of the user whom last updated the project.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUpdatedUserIdentifier();
}
