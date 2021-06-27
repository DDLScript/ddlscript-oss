package com.ddlscript.app.routes.api.projects;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.inputs.projects.UpdateProjectInput;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import spark.Request;
import spark.Response;

public class DeleteProjectRoute extends AbstractAuthenticatedRoute<UpdateProjectInput, String> {

	public DeleteProjectRoute() {
		super();
	}

	@Override
	public String handle(
			final AuthenticationContext withAuthenticationContext
			, final UpdateProjectInput withInput
			, final Request request
			, final Response response
	) throws Exception {
		// fetch the project to be deleted
		var describeProjectRequest = DescribeProjectRequest.builder()
				.setIdentifier(new ProjectIdentifier(Integer.parseInt(request.params("project"))))
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.describe(describeProjectRequest)
				.orElseThrow();

		// check that the user has permission
		if (!withAuthenticationContext.getSystemPermissions().contains(SystemPermission.DELETE_PROJECTS)) {
			return null;
		}

		// delete the project
		ControllerFactory.INSTANCE
				.getProjectController()
				.delete(projectModel);

		return "";
	}
}
