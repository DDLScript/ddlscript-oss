package com.ddlscript.schema.projects;

import com.ddlscript.def.models.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.models.permissions.project.ProjectPermission;
import com.ddlscript.def.models.projects.ProjectModel;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AuthenticationContext;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import java.util.Collection;

public class ProjectSchema extends ProjectSummarizedSchema {

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
