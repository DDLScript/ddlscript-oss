package com.ddlscript.routes;

import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.sdk.AbstractRoute;
import lombok.NonNull;
import spark.Request;
import spark.Response;

public abstract class AbstractAuthenticatedRoute<INPUT, OUTPUT> extends AbstractRoute<INPUT, OUTPUT> {

	public abstract OUTPUT handle(
			final AuthenticationContext withAuthenticationContext, final INPUT withBody, final Request request, final Response response
	) throws Exception;

	public AbstractAuthenticatedRoute() {
		super();
	}

	public AbstractAuthenticatedRoute(@NonNull Class<INPUT> withInputClass) {
		super(withInputClass);
	}

	@Override
	public OUTPUT handle(final INPUT withBody, final Request request, final Response response) throws Exception {
		final var sessionId = request.cookie("sesid");
		if (sessionId == null || sessionId.isBlank()) {
			return null;
		}

		var sessionModel = ControllerFactory.INSTANCE.getSessionController()
				.describe(builder -> builder.setToken(sessionId))
				.orElseThrow();

		// search for a user
		var userModel = ControllerFactory.INSTANCE
				.getUserController()
				.find(sessionModel.getUserIdentifier())
				.orElseThrow();

		var authenticationContext = AuthenticationContext.builder()
				.setSessionModel(sessionModel)
				.setUserModel(userModel)
				.build();


		return this.handle(authenticationContext, withBody, request, response);
	}
}
