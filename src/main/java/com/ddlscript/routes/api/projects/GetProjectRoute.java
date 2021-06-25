package com.ddlscript.routes.api.projects;

import com.ddlscript.def.models.projects.DescribeProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
import com.ddlscript.schema.projects.ProjectSchema;
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
