package com.ddlscript.app.routes.api.projects;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.inputs.projects.UpdateProjectInput;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.projects.ProjectSchema;
import com.ddlscript.def.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.UpdateProjectRequest;
import spark.Request;
import spark.Response;

import java.util.function.Consumer;

/**
 * API Route for updating a project's details.
 */
public class PatchProjectRoute extends AbstractAuthenticatedRoute<UpdateProjectInput, ProjectSchema> {

	/**
	 * Default constructor.
	 */
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
				.applyMutation(this.getTemplatesFromRequest(withInput))
				.build();
		return ControllerFactory.INSTANCE
				.getProjectController()
				.update(projectModel, updateRequest)
				.map(model -> new ProjectSchema(withAuthenticationContext, model))
				.orElseThrow();
	}

	/**
	 * Returns a consumer which will populate a builder with script templates.
	 *
	 * @param withInput
	 * 		User's input
	 * @return Builder consumer.
	 */
	private Consumer<UpdateProjectRequest.Builder> getTemplatesFromRequest(final UpdateProjectInput withInput) {
		return builder -> {
			final var templateInput = withInput.getTemplate();

			if (templateInput == null) {
				return;
			}

			builder.setTemplateBeforeAll(templateInput.getBeforeAll());
			builder.setTemplateAfterAll(templateInput.getAfterAll());
			builder.setTemplateBeforeEach(templateInput.getBeforeEach());
			builder.setTemplateAfterEach(templateInput.getAfterEach());
		};
	}
}
