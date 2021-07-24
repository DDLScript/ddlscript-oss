package com.ddlscript.def.projects.scripts;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.sdk.models.IdentifiableModel;

import java.time.Instant;

/**
 * Represents a single project within the system.
 */
public interface ScriptModel extends IdentifiableModel<ScriptIdentifier>, AuditedModel {

	/**
	 * Maximum number of characters allowed in the TITLE field.
	 */
	int MAX_TITLE_LENGTH = 300;

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
	 * Returns the identifier of the user whom last updated the project.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUpdatedUserIdentifier();
}
