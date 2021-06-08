package com.ddlscript.def.models;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.SdkBuilder;

/**
 * Represents a request to create a new model.
 *
 * @param <MODEL>
 * 		Data type of the model being created.
 */
public interface CreatableRequest<MODEL extends Model> {

	/**
	 * Represents a builder instance that is capable of producing a creation request instance.
	 *
	 * @param <MODEL>
	 * 		Data type of the model the generated request instance will create.
	 * @param <REQUEST>
	 * 		Data type of the request instance being built.
	 * @param <BUILDER>
	 * 		Data type of the builder instance.
	 */
	interface CreatableRequestBuilder<MODEL extends Model, REQUEST extends CreatableRequest<MODEL>,
			BUILDER extends CreatableRequest.CreatableRequestBuilder<MODEL, REQUEST, BUILDER>>
			extends SdkBuilder<REQUEST, BUILDER> {
	}
}
