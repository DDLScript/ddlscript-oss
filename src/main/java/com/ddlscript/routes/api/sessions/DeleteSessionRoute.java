package com.ddlscript.routes.api.sessions;

import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
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
