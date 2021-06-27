package com.ddlscript.app.routes.api.projects.scripts;

import com.ddlscript.def.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.scripts.CreateScriptRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.inputs.projects.scripts.CreateScriptInput;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.projects.scripts.ScriptSchema;
import spark.Request;
import spark.Response;

public class PostScriptRoute extends AbstractAuthenticatedRoute<CreateScriptInput, ScriptSchema> {

	public PostScriptRoute() {
		super(CreateScriptInput.class);
	}

	@Override
	public ScriptSchema handle(
			final AuthenticationContext withAuthenticationContext
			, final CreateScriptInput withInput
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
		if (!projectPermissions.contains(ProjectPermission.MODIFY_SCRIPTS)) {
			return null;
		}

		// create the new script
		var createScriptRequest = CreateScriptRequest.builder()
				.setTitle(withInput.getTitle())
				.setProject(projectModel)
				.setUserCreated(withAuthenticationContext.getUserModel())
				.build();
		var scriptModel = ControllerFactory.INSTANCE
				.getScriptController()
				.create(createScriptRequest)
				.orElseThrow();

		// return the new session schema
		return new ScriptSchema(withAuthenticationContext, scriptModel);
	}
}
