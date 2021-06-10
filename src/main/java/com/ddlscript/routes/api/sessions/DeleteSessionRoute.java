package com.ddlscript.routes.api.sessions;

import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import spark.Request;
import spark.Response;

public class DeleteSessionRoute extends AbstractAuthenticatedRoute<Void, String> {

	@Override
	public String handle(
			final SessionModel withSession
			, final Void withBody
			, final Request request
			, final Response response
	) throws Exception {

		ControllerFactory.INSTANCE
				.getSessionController()
				.delete(withSession);

		// clear the cookie
		response.cookie("sesid", "", 0, false, true);

		// success
		return "";
	}
}
