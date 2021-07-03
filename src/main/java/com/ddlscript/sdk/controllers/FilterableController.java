package com.ddlscript.sdk.controllers;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.sdk.models.FilterableRequest;

/**
 * Represents a controller that is capable of actioning a Describable request.
 *
 * @param <MODEL>
 * 		Data type of the model being described.
 * @param <REQUEST>
 * 		Data type of the Describable request.
 */
public interface FilterableController<MODEL extends Model, REQUEST extends FilterableRequest<MODEL>> {
	/**
	 * Returns a collection of models based upon the request that filters by a unique value.
	 *
	 * @param withRequest
	 * 		Describe Request.
	 * @return paginated collection of models
	 */
	PaginatedCollection<MODEL> filter(REQUEST withRequest);
}
