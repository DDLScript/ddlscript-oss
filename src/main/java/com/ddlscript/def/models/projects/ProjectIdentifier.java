package com.ddlscript.def.models.projects;

import com.ddlscript.def.models.AbstractPositiveIdentifier;

/**
 * Represents the identifier of a Project.
 */
public class ProjectIdentifier extends AbstractPositiveIdentifier {

	/**
	 * Default constructor that accepts a raw identifier value.
	 *
	 * @param withRawValue Raw Identifier
	 */
	public ProjectIdentifier(final int withRawValue) {
		super(withRawValue);
	}
}
