package com.ddlscript.def.projects.usergroups;

import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.usergroups.UserGroupIdentifier;
import com.ddlscript.def.users.UserIdentifier;

import java.time.Instant;

/**
 * Represents a single user group assigned to a project.
 */
public interface ProjectUserGroupModel {

	/**
	 * Returns the timestamp of when the user group was added to the project.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the identifier of the project the user group is linked to.
	 *
	 * @return Project Identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the user group being assigned to the project
	 *
	 * @return User Group Identifier.
	 */
	UserGroupIdentifier getUserGroupIdentifier();

	/**
	 * Returns the identifier of the user whom linked the user group to the project
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();
}
