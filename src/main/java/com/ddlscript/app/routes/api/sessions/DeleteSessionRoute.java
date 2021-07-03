package com.ddlscript.app.routes.api.sessions;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import spark.Request;
import spark.Response;

public class DeleteSessionRoute extends AbstractAuthenticatedRoute<Void, String> {

	@Override
	public String handle(
			final AuthenticationContext withAuthenticationContext
			, final Void withBody
			, final Request request
			, final Response response
	) throws Exception {

		ControllerFactory.INSTANCE
				.getSessionController()
				.delete(withAuthenticationContext.getSessionModel());

		// clear the cookie
		response.cookie("sesid", "", 0, false, true);

		// success
		return "";
	}
}
