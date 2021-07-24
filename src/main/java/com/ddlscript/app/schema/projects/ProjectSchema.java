package com.ddlscript.app.schema.projects;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.common.AuditedSchema;
import com.ddlscript.def.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.permissions.project.ProjectPermission;
import com.ddlscript.def.projects.ProjectModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}
