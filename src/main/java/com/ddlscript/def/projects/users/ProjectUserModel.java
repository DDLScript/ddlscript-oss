package com.ddlscript.def.projects.users;

import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single user assigned to a project.
 */
public interface ProjectUserModel {

	/**
	 * Returns the timestamp of when the user was added to the project.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the identifier of the project the user is linked to.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user being assigned to the project
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUserIdentifier();

	/**
	 * Returns the identifier of the user whom linked the user to the project
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
