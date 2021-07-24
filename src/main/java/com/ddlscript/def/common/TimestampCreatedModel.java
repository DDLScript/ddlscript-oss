package com.ddlscript.def.common;

import com.ddlscript.sdk.Model;

import java.time.Instant;

/**
 * Represents a model with a creation timestamp.
 */
public interface TimestampCreatedModel extends Model {

	/**
	 * Returns the timestamp of when the instance was created.
	 *
	 * @return Creation timestamp.
	 */
	Instant getTimestampCreated();
}
