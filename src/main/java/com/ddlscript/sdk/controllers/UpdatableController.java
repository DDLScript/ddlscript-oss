package com.ddlscript.sdk.controllers;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.models.UpdatableRequest;

import java.util.Optional;

/**
 * Represents a controller that is capable of actioning an Update request.
 *
 * @param <MODEL>
 * 		Data type of the model being updated.
 * @param <REQUEST>
 * 		Data type of the update request.
 */
public interface UpdatableController<MODEL extends Model, REQUEST extends UpdatableRequest<MODEL>> {

	/**
	 * Returns an updated model based upon the given update request.
	 *
	 * @param withModel
	 *      Model to update.
	 * @param withRequest
	 * 		Update Request.
	 * @return Optional describing a Model.
	 */
	Optional<MODEL> update(MODEL withModel, REQUEST withRequest);
}
