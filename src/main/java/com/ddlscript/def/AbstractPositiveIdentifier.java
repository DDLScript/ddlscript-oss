package com.ddlscript.def;

import com.ddlscript.sdk.Identifier;
import lombok.Getter;

/**
 * Positive integer based identifier.
 */
public abstract class AbstractPositiveIdentifier implements Identifier {

	/**
	 * Raw integer value.
	 */
	@Getter
	private final int rawValue;

	/**
	 * Default constructor.
	 *
	 * @param withRawValue
	 * 		raw identifier value.
	 * @throws IllegalArgumentException
	 * 		if the provided value is not greater than zero
	 */
	protected AbstractPositiveIdentifier(final int withRawValue) {
		if (withRawValue < 0) {
			throw new IllegalArgumentException("Provided value must be greater then or equal to zero.");
		}

		this.rawValue = withRawValue;
	}
}
