package com.ddlscript.sdk.models;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.SdkBuilder;

/**
 * Represents a request to describe a model.
 *
 * @param <MODEL>
 * 		Data type of the model being described.
 */
public interface FilterableRequest<MODEL extends Model> {

	/**
	 * Represents a builder instance that is capable of producing a describable request instance.
	 *
	 * @param <MODEL>
	 * 		Data type of the model the generated request instance will describe.
	 * @param <REQUEST>
	 * 		Data type of the describable instance being built.
	 * @param <BUILDER>
	 * 		Data type of the builder instance.
	 */
	interface FilterableRequestBuilder<MODEL extends Model, REQUEST extends FilterableRequest<MODEL>,
			BUILDER extends FilterableRequestBuilder<MODEL, REQUEST, BUILDER>>
			extends SdkBuilder<REQUEST, BUILDER> {
	}
}
