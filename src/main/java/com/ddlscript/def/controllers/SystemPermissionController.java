package com.ddlscript.def.controllers;

import com.ddlscript.def.models.permissions.system.FilterSystemPermissionRequest;
import com.ddlscript.def.models.permissions.system.SystemPermission;
import com.ddlscript.sdk.controllers.FilterableController;

/**
 * Represents a controller capable of providing information about system permissions.
 */
public interface SystemPermissionController
		extends FilterableController<SystemPermission, FilterSystemPermissionRequest> {
}
