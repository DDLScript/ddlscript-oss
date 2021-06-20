package com.ddlscript.def.models.projects.scripts;

import com.ddlscript.def.models.AbstractPositiveIdentifier;

/**
 * Represents the identifier of a Script.
 */
public class ScriptIdentifier extends AbstractPositiveIdentifier {

	/**
	 * Default constructor that accepts a raw identifier value.
	 *
	 * @param withRawValue Raw Identifier
	 */
	public ScriptIdentifier(final int withRawValue) {
		super(withRawValue);
	}
}
