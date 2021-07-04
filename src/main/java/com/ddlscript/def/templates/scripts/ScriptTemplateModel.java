package com.ddlscript.def.templates.scripts;

import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.scripts.ScriptIdentifier;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.sdk.Model;

import java.time.Instant;
import java.util.Optional;

/**
 * Script Template.
 */
public interface ScriptTemplateModel extends Model {

	/**
	 * Returns the timestamp of when the template was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();

	/**
	 * Returns the timestamp of when the template was last updated.
	 *
	 * @return Last Updated timestamp.
	 */
	Instant getTimestampUpdated();

	/**
	 * Returns the identifier of the project this template belongs to.
	 *
	 * @return Project identifier.
	 */
	ProjectIdentifier getProjectIdentifier();

	/**
	 * Returns the identifier of the script this template has been specifically applied to, or {@code null} to
	 * represent
	 * the project's default script.
	 *
	 * @return Script Identifier or {@code null}.
	 */
	ScriptIdentifier getScriptIdentifier();

	/**
	 * Returns an optional describing the identifier of the script this template has been specifically applied to.
	 *
	 * @return Optional of a Script Identifier.
	 */
	default Optional<ScriptIdentifier> optScriptIdentifier() {
		return Optional.ofNullable(this.getScriptIdentifier());
	}

	/**
	 * Returns whether the template to being applied to each statement with the script or just to the overall script
	 * itself.
	 *
	 * @return {@code true} to apply the template to each statement within the script, or {@code false} to apply the
	 * template to the entire script itself.
	 */
	boolean isAppliedToEachStatement();

	/**
	 * Returns whether the template is applied before or after it's intended location.
	 *
	 * @return {@code true} if the template is applied after it's intended target, or {@code false} if applied before.
	 */
	boolean isAppliedAfter();

	/**
	 * Returns the SQL query to be applied as a template.
	 *
	 * @return SQL.
	 */
	String getQuery();

	/**
	 * Returns the identifier of the user whom created the template.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getCreatedUserIdentifier();

	/**
	 * Returns the identifier of the user whom last updated the template.
	 *
	 * @return User Identifier.
	 */
	UserIdentifier getUpdatedUserIdentifier();

	/**
	 * Returns an optional describing the identifier of the user whom last updated the template.
	 *
	 * @return Optional of a User Identifier.
	 */
	default Optional<UserIdentifier> optUpdatedUserIdentifier() {
		return Optional.ofNullable(this.getUpdatedUserIdentifier());
	}
}
