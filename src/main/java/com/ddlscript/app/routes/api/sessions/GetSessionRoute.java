package com.ddlscript.app.routes.api.sessions;

import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.sessions.SessionSchema;
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
		return new SessionSchema(withAuthenticationContext);
	}
}
