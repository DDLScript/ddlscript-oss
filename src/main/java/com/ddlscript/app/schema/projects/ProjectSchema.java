package com.ddlscript.app.schema.projects;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.common.AuditedSchema;
import com.ddlscript.def.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.ProjectModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;

public class ProjectSchema extends ProjectSummarizedSchema implements AuditedSchema {

	@JsonIgnore
	private final AuthenticationContext authenticationContext;

	public ProjectSchema(
			@NonNull final AuthenticationContext withAuthenticationContext, @NonNull final ProjectModel withModel
	) {
		super(withModel);
		this.authenticationContext = withAuthenticationContext;
	}

	@JsonProperty("permissions")
	public Collection<ProjectPermission> getProjectPermissions() {
		var filter = FilterProjectPermissionRequest.builder()
				.setProject(this.getModel())
				.setAccessibleToUser(this.authenticationContext.getUserModel())
				.build();
		return ControllerFactory.INSTANCE
				.getProjectPermissionController()
				.filter(filter)
				.getElements();
	}

	/**
	 * Returns the templates being applied to the project.
	 *
	 * @return Project templates.
	 */
	@JsonProperty("templates")
	public Template getTemplates() {
		return new Template(this.getModel());
	}

	/**
	 * Template Schema.
	 */
	@Getter
	public static class Template {

		/**
		 * SQL Template to appear at the start of each script.
		 */
		@JsonProperty("before_all")
		private final String beforeAll;

		/**
		 * SQL Template to appear before each statement on a script.
		 */
		@JsonProperty("before_each")
		private final String beforeEach;

		/**
		 * SQL Template to appear after each statement on a string.
		 */
		@JsonProperty("after_each")
		private final String afterEach;

		/**
		 * SQL Template to appear at the end of each script.
		 */
		@JsonProperty("after_all")
		private final String afterAll;

		/**
		 * Instantiates a new instance with the given project model.
		 *
		 * @param withModel
		 * 		Project Model.
		 */
		public Template(@NonNull final ProjectModel withModel) {
			this.beforeAll = withModel.getTemplateBeforeAll();
			this.beforeEach = withModel.getTemplateBeforeEach();
			this.afterEach = withModel.getTemplateAfterEach();
			this.afterAll = withModel.getTemplateAfterAll();
		}
	}
}
