package com.ddlscript.routes.api.sessions;

import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
import com.ddlscript.schema.sessions.SessionSchema;
import spark.Request;
import spark.Response;

public class GetSessionRoute extends AbstractAuthenticatedRoute<Void, SessionSchema> {

	@Override
	public SessionSchema handle(
			final AuthenticationContext withAuthenticationContext
			, final Void withBody
			, final Request request
			, final Response response
	) throws Exception {
		return new SessionSchema(withAuthenticationContext.getSessionModel());
	}
}
