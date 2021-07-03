package com.ddlscript.def.projects;

import com.ddlscript.def.AbstractPositiveIdentifier;

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
