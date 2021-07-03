package com.ddlscript.app.routes.api.projects;

import com.ddlscript.def.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.UpdateProjectRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.inputs.projects.UpdateProjectInput;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.projects.ProjectSchema;
import spark.Request;
import spark.Response;

public class PatchProjectRoute extends AbstractAuthenticatedRoute<UpdateProjectInput, ProjectSchema> {

	public PatchProjectRoute() {
		super(UpdateProjectInput.class);
	}

	@Override
	public ProjectSchema handle(
			final AuthenticationContext withAuthenticationContext
			, final UpdateProjectInput withInput
			, final Request request
			, final Response response
	) throws Exception {
		// create a new session instance
		var describeProjectRequest = DescribeProjectRequest.builder()
				.setIdentifier(new ProjectIdentifier(Integer.parseInt(request.params("project"))))
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.describe(describeProjectRequest)
				.orElseThrow();

		// check that the user has permission
		var filterProjectPermissionRequest = FilterProjectPermissionRequest.builder()
				.setProject(projectModel)
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var projectPermissions = ControllerFactory.INSTANCE
				.getProjectPermissionController()
				.filter(filterProjectPermissionRequest)
				.getElements();
		if (!projectPermissions.contains(ProjectPermission.MANAGE_PROJECT_SETTINGS)) {
			return null;
		}

		// update the project's details
		var updateRequest = UpdateProjectRequest.builder()
				.setName(withInput.getName())
				.setUserUpdated(withAuthenticationContext.getUserModel())
				.build();
		return ControllerFactory.INSTANCE
				.getProjectController()
				.update(projectModel, updateRequest)
				.map(model -> new ProjectSchema(withAuthenticationContext, model))
				.orElseThrow();
	}
}
