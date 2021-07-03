package com.ddlscript.app.routes.api.projects;

import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.projects.ProjectSchema;
import spark.Request;
import spark.Response;

public class GetProjectRoute extends AbstractAuthenticatedRoute<Void, ProjectSchema> {

	public GetProjectRoute() {
		super();
	}

	@Override
	public ProjectSchema handle(
			final AuthenticationContext withAuthenticationContext
			, final Void withInput
			, final Request request
			, final Response response
	) throws Exception {
//		if (!withAuthenticationContext.getSystemPermissions()
//				.contains(SystemPermission.CREATE_PROJECTS)) {
//			throw new IllegalAccessException();
//		}

		// create a new session instance
		var describeProjectRequest = DescribeProjectRequest.builder()
				.setIdentifier(new ProjectIdentifier(Integer.parseInt(request.params("project"))))
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.describe(describeProjectRequest)
				.orElseThrow();

		// return the new session schema
		return new ProjectSchema(withAuthenticationContext, projectModel);
	}
}
