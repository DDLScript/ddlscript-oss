package com.ddlscript.def.models.projects;

import com.ddlscript.def.models.IdentifiableModel;
import com.ddlscript.def.models.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single project within the system.
 */
public interface ProjectModel extends IdentifiableModel<ProjectIdentifier> {

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_NAME_LENGTH = 100;

	/**
	 * Returns the timestamp of when the project was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the timestamp of when the project was deleted.
	 *
	 * @return Deleted timestamp.
	 */
	Instant getTimestampDeleted();

	/**
	 * Returns the name of the project.
	 *
	 * @return Name of the project.
	 */
	String getName();

	/**
	 * Returns the database type this project is based upon..
	 *
	 * @return database type.
	 */
	String getDatabaseType();

	/**
	 * Returns the identifier of the user whom created the project.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
