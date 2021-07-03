package com.ddlscript.app.routes.api.projects;

import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.projects.CreateProjectRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.inputs.projects.CreateProjectInput;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.projects.ProjectSchema;
import spark.Request;
import spark.Response;

public class PostProjectRoute extends AbstractAuthenticatedRoute<CreateProjectInput, ProjectSchema> {

	public PostProjectRoute() {
		super(CreateProjectInput.class);
	}

	@Override
	public ProjectSchema handle(
			final AuthenticationContext withAuthenticationContext
			, final CreateProjectInput withInput
			, final Request request
			, final Response response
	) throws Exception {
		if (!withAuthenticationContext.getSystemPermissions()
				.contains(SystemPermission.CREATE_PROJECTS)) {
			throw new IllegalAccessException();
		}

		// create a new session instance
		var createProjectRequest = CreateProjectRequest.builder()
				.setName(withInput.getName())
				.setUserCreated(withAuthenticationContext.getUserModel())
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.create(createProjectRequest)
				.orElseThrow();

		// return the new session schema
		return new ProjectSchema(withAuthenticationContext, projectModel);
	}
}
