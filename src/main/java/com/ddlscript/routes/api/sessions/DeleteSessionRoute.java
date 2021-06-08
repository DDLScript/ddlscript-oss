package com.ddlscript.routes.api.sessions;

import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.sdk.AbstractRoute;
import spark.Request;
import spark.Response;

public class DeleteSessionRoute extends AbstractRoute<Void, String> {

	@Override
	public String handle(final Void withBody, final Request request, final Response response) throws Exception {

		final var sessionId = request.cookie("sesid");
		if (sessionId == null || sessionId.isBlank()) {
			return null;
		}

		var sessionModel = ControllerFactory.INSTANCE
				.getSessionController()
				.describe(builder -> builder.setToken(sessionId))
				.orElseThrow();

		ControllerFactory.INSTANCE
				.getSessionController()
				.delete(sessionModel);

		// clear the cookie
		response.cookie("sesid", "", 0, false, true);

		// success
		return "";
	}
}
