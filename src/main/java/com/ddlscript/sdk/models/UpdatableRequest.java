package com.ddlscript.sdk.models;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.SdkBuilder;

/**
 * Represents a request to update an existing model.
 *
 * @param <MODEL>
 * 		Data type of the model being created.
 */
public interface UpdatableRequest<MODEL extends Model> {

	/**
	 * Represents a builder instance that is capable of producing an updatable request instance.
	 *
	 * @param <MODEL>
	 * 		Data type of the model the generated request instance will create.
	 * @param <REQUEST>
	 * 		Data type of the request instance being built.
	 * @param <BUILDER>
	 * 		Data type of the builder instance.
	 */
	interface UpdatableRequestBuilder<MODEL extends Model, REQUEST extends UpdatableRequest<MODEL>,
			BUILDER extends UpdatableRequest.UpdatableRequestBuilder<MODEL, REQUEST, BUILDER>>
			extends SdkBuilder<REQUEST, BUILDER> {
	}
}
