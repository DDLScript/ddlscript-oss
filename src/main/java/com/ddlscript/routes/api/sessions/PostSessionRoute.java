package com.ddlscript.routes.api.sessions;

import com.ddlscript.def.models.sessions.CreateSessionRequest;
import com.ddlscript.def.models.users.DescribeUserRequest;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.inputs.sessions.CreateSessionInput;
import com.ddlscript.schema.sessions.SessionSchema;
import com.ddlscript.sdk.AbstractRoute;
import spark.Request;
import spark.Response;

public class PostSessionRoute extends AbstractRoute<CreateSessionInput, SessionSchema> {

	public PostSessionRoute() {
		super(CreateSessionInput.class);
	}

	@Override
	public SessionSchema handle(final CreateSessionInput withInput, final Request request, final Response response) throws Exception {
		// check if a session already exists
//		final var sessionId = request.cookie("sesid");
//		if (sessionId == null || sessionId.isBlank()) {
//			return null;
//		}

		// search for a user
		var describeUserRequest = DescribeUserRequest.builder()
				.setUsername(withInput.getUsername())
				.build();
		var userModel = ControllerFactory.INSTANCE
				.getUserController()
				.describe(describeUserRequest)
				.orElseThrow();

		// validate password
		if (!userModel.verifyPassword(withInput.getPassword())) {
			// ensure cookie is removed
			response.cookie("sesid", "", 0, false, true);
			return null;
		}

		// create a new session instance
		var createSessionRequest = CreateSessionRequest.builder()
				.setUser(userModel)
				.build();
		var sessionModel = ControllerFactory.INSTANCE
				.getSessionController()
				.create(createSessionRequest)
				.orElseThrow();

		// store the session cookie
		response.cookie("sesid", sessionModel.getToken(), withInput.isRememberMe() ? Integer.MAX_VALUE : -1, false, true);

		// return the new session schema
		return new SessionSchema(sessionModel);
	}
}
