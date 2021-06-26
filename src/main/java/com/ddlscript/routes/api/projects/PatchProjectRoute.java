package com.ddlscript.routes.api.projects;

import com.ddlscript.def.models.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.models.permissions.project.ProjectPermission;
import com.ddlscript.def.models.projects.DescribeProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.UpdateProjectRequest;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.inputs.projects.UpdateProjectInput;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
import com.ddlscript.schema.projects.ProjectSchema;
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
