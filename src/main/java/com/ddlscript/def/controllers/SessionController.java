package com.ddlscript.def.controllers;

import com.ddlscript.def.models.sessions.CreateSessionRequest;
import com.ddlscript.def.models.sessions.DescribeSessionRequest;
import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.sdk.controllers.DeletableController;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents a controller capable of providing information about sessions.
 */
public interface SessionController extends DeletableController<SessionModel> {

	/**
	 * Returns a single model based upon the request that filters by a unique value.
	 *
	 * @param withRequest
	 *      Describe Request.
	 * @return Optional describing a Session Model.
	 */
	Optional<SessionModel> describe(DescribeSessionRequest withRequest);

	/**
	 * Returns a single model based upon the request that filters by a unique value.
	 *
	 * @param withConsumer
	 *      Consumer that accepts a request builder.
	 * @return Optional describing a Session Model.
	 */
	default Optional<SessionModel> describe(@NonNull Consumer<DescribeSessionRequest.DescribeSessionRequestBuilder> withConsumer) {
		DescribeSessionRequest request = DescribeSessionRequest.builder()
				.applyMutation(withConsumer)
				.build();

		return this.describe(request);
	}

	/**
	 * Returns a newly created model based upon the given creation request.
	 *
	 * @param withRequest
	 *      Creation Request.
	 * @return Optional describing a Session Model.
	 */
	Optional<SessionModel> create(CreateSessionRequest withRequest);

	/**
	 * Returns a newly created model based upon the given creation request.
	 *
	 * @param withConsumer
	 *      Consumer that accepts a request builder.
	 * @return Optional describing a newly created Session Model.
	 */
	default Optional<SessionModel> create(@NonNull Consumer<CreateSessionRequest.CreateSessionRequestBuilder> withConsumer) {
		CreateSessionRequest request = CreateSessionRequest.builder()
				.applyMutation(withConsumer)
				.build();

		return this.create(request);
	}
}
