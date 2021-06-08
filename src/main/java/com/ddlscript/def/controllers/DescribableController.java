package com.ddlscript.def.controllers;

import com.ddlscript.def.models.DescribableRequest;
import com.ddlscript.sdk.Model;

import java.util.Optional;

/**
 * Represents a controller that is capable of actioning a Describable request.
 *
 * @param <MODEL>
 * 		Data type of the model being described.
 * @param <REQUEST>
 * 		Data type of the Describable request.
 */
public interface DescribableController<MODEL extends Model, REQUEST extends DescribableRequest<MODEL>> {
	/**
	 * Returns a single model based upon the request that filters by a unique value.
	 *
	 * @param withRequest
	 * 		Describe Request.
	 * @return Optional describing a Session Model.
	 */
	Optional<MODEL> describe(REQUEST withRequest);
}
