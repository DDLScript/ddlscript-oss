package com.ddlscript.routes.api.sessions;

import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.schema.sessions.SessionSchema;
import spark.Request;
import spark.Response;

public class GetSessionRoute extends AbstractAuthenticatedRoute<Void, SessionSchema> {

	@Override
	public SessionSchema handle(
			final SessionModel withSession
			, final Void withBody
			, final Request request
			, final Response response
	) throws Exception {
		return new SessionSchema(withSession);
	}
}
