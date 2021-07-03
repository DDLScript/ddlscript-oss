package com.ddlscript.def.permissions.system;

import com.ddlscript.sdk.controllers.FilterableController;

/**
 * Represents a controller capable of providing information about system permissions.
 */
public interface SystemPermissionController
		extends FilterableController<SystemPermission, FilterSystemPermissionRequest> {
}
