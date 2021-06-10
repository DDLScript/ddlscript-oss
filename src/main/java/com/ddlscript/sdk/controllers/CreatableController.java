package com.ddlscript.sdk.controllers;

import com.ddlscript.def.models.CreatableRequest;
import com.ddlscript.sdk.Model;

import java.util.Optional;

/**
 * Represents a controller that is capable of actioning a Creation request.
 *
 * @param <MODEL>
 * 		Data type of the model being created.
 * @param <REQUEST>
 * 		Data type of the creation request.
 */
public interface CreatableController<MODEL extends Model, REQUEST extends CreatableRequest<MODEL>> {

	/**
	 * Returns a newly created model based upon the given creation request.
	 *
	 * @param withRequest
	 * 		Creation Request.
	 * @return Optional describing a Session Model.
	 */
	Optional<MODEL> create(REQUEST withRequest);
}
