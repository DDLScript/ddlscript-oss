package com.ddlscript.def.controllers;

import com.ddlscript.def.models.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.models.permissions.project.ProjectPermission;
import com.ddlscript.sdk.controllers.FilterableController;

/**
 * Represents a controller capable of providing information about system permissions.
 */
public interface ProjectPermissionController
		extends FilterableController<ProjectPermission, FilterProjectPermissionRequest> {
}
