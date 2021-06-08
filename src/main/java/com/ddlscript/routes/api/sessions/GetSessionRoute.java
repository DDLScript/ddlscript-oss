package com.ddlscript.routes.api.sessions;

import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.schema.sessions.SessionSchema;
import com.ddlscript.sdk.AbstractRoute;
import spark.Request;
import spark.Response;

public class GetSessionRoute extends AbstractRoute<Void, SessionSchema> {

	@Override
	public SessionSchema handle(final Void withBody, final Request request, final Response response) throws Exception {

		final var sessionId = request.cookie("sesid");
		if (sessionId == null || sessionId.isBlank()) {
			return null;
		}

		var sessionModel = ControllerFactory.INSTANCE.getSessionController()
				.describe(builder -> builder.setToken(sessionId))
				.orElseThrow();

		return new SessionSchema(sessionModel);
	}
}
