package com.ddlscript.def.models;

import com.ddlscript.sdk.Model;
import com.ddlscript.sdk.SdkBuilder;

/**
 * Represents a request to describe a model.
 *
 * @param <MODEL>
 * 		Data type of the model being described.
 */
public interface DescribableRequest<MODEL extends Model> {

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
	interface DescribableRequestBuilder<MODEL extends Model, REQUEST extends DescribableRequest<MODEL>,
			BUILDER extends DescribableRequestBuilder<MODEL, REQUEST, BUILDER>>
			extends SdkBuilder<REQUEST, BUILDER> {
	}
}
