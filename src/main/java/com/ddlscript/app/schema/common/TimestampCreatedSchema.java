package com.ddlscript.app.schema.common;

import com.ddlscript.def.common.TimestampCreatedModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Schema representing instance with a creation timestamp.
 */
public interface TimestampCreatedSchema {

	/**
	 * Fetches an instance of the underlying model.
	 *
	 * @return Timestamped Model.
	 */
	@JsonIgnore
	TimestampCreatedModel getModel();

	@JsonProperty("timestamp_created")
	default String getTimestampCreated() {
		return this.getModel()
				.getTimestampCreated()
				.toString();
	}

}
