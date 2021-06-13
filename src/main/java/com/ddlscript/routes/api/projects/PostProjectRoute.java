package com.ddlscript.routes.api.projects;

import com.ddlscript.def.models.projects.CreateProjectRequest;
import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.inputs.projects.CreateProjectInput;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.schema.projects.ProjectSchema;
import spark.Request;
import spark.Response;

public class PostProjectRoute extends AbstractAuthenticatedRoute<CreateProjectInput, ProjectSchema> {

	public PostProjectRoute() {
		super(CreateProjectInput.class);
	}

	@Override
	public ProjectSchema handle(
			final SessionModel withSessionModel
			, final CreateProjectInput withInput
			, final Request request
			, final Response response
	) throws Exception {
		// check if a session already exists
//		final var sessionId = request.cookie("sesid");
//		if (sessionId == null || sessionId.isBlank()) {
//			return null;
//		}

		// search for a user
		var userModel = ControllerFactory.INSTANCE
				.getUserController()
				.find(withSessionModel.getUserIdentifier())
				.orElseThrow();

		// create a new session instance
		var createProjectRequest = CreateProjectRequest.builder()
				.setName(withInput.getName())
				.setUserCreated(userModel)
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.create(createProjectRequest)
				.orElseThrow();

		// return the new session schema
		return new ProjectSchema(projectModel);
	}
}
