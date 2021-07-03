package com.ddlscript.def.authentication;

import lombok.NonNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents a controller capable of providing information about sessions.
 */
public interface AuthenticationController {

	/**
	 * Returns a single model based upon the request that filters by a unique value.
	 *
	 * @param withRequest
	 * 		Describe Request.
	 * @return Optional describing a Session Model.
	 */
	Optional<AuthenticationModel> describe(DescribeAuthenticationRequest withRequest);

	/**
	 * Returns a single model based upon the request that filters by a unique value.
	 *
	 * @param withConsumer
	 * 		Consumer that accepts a request builder.
	 * @return Optional describing a Session Model.
	 */
	default Optional<AuthenticationModel> describe(
			@NonNull Consumer<DescribeAuthenticationRequest.DescribeAuthenticationRequestBuilder> withConsumer
	) {
		DescribeAuthenticationRequest request = DescribeAuthenticationRequest.builder()
				.applyMutation(withConsumer)
				.build();

		return this.describe(request);
	}

}
