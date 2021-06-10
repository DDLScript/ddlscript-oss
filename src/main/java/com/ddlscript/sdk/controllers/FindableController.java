package com.ddlscript.sdk.controllers;

import com.ddlscript.def.models.IdentifiableModel;
import com.ddlscript.sdk.Identifier;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a controller that is capable of retrieving a model based upon it's identifier value.
 *
 * @param <IDENTIFIER>
 * 		Data type of the model's identifier.
 * @param <MODEL>
 * 		Data type of the model being retrieved.
 */
public interface FindableController<IDENTIFIER extends Identifier, MODEL extends IdentifiableModel<IDENTIFIER>> {

	/**
	 * Returns a single model with the specified identifier.
	 *
	 * @param withIdentifier
	 * 		Identifier of the model to retrieve.
	 * @return an optional describing the requested model.
	 */
	Optional<MODEL> find(IDENTIFIER withIdentifier);

	/**
	 * Returns a collection of models based specified identifier values.
	 *
	 * @param withIdentifiers
	 * 		Collection of identifiers.
	 * @return collection of models.
	 */
	default Collection<MODEL> find(Collection<IDENTIFIER> withIdentifiers) {
		return withIdentifiers.stream()
				.map(this::find)
				.flatMap(Optional::stream)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}
