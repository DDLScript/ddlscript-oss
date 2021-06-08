package com.ddlscript.def.models;

import com.ddlscript.sdk.Identifier;
import com.ddlscript.sdk.Model;

/**
 * Represents a model containing an identifier.
 *
 * @param <IDENTIFIER>
 * 		Data type of the identifier.
 */
public interface IdentifiableModel<IDENTIFIER extends Identifier> extends Model {

	/**
	 * Returns the identifier of the model.
	 *
	 * @return Model's identifier.
	 */
	IDENTIFIER getIdentifier();
}
