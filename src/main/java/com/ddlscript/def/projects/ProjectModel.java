package com.ddlscript.def.projects;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.sdk.models.IdentifiableModel;

import java.time.Instant;

/**
 * Represents a single project within the system.
 */
public interface ProjectModel extends IdentifiableModel<ProjectIdentifier>, AuditedModel {

	/**
	 * Maximum number of characters allowed in the NAME field.
	 */
	int MAX_NAME_LENGTH = 100;

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
	 * Returns the query template to apply before every script.
	 *
	 * @return SQL query template.
	 */
	String getTemplateBeforeAll();

	/**
	 * Returns the query template to apply before each statement on a script
	 *
	 * @return SQL query template.
	 */
	String getTemplateBeforeEach();

	/**
	 * Returns the query template to aplly after each statement on a script.
	 *
	 * @return SQL query template.
	 */
	String getTemplateAfterEach();

	/**
	 * Returns the query template to apply after every script.
	 *
	 * @return SQL query template.
	 */
	String getTemplateAfterAll();


}
