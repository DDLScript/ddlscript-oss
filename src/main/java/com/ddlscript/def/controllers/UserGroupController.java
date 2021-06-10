package com.ddlscript.def.controllers;

import com.ddlscript.def.models.usergroups.CreateUserGroupRequest;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.usergroups.UserGroupModel;
import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.FindableController;

/**
 * Controller for managing User Groups.
 */
public interface UserGroupController extends FindableController<UserGroupIdentifier, UserGroupModel>
		, CreatableController<UserGroupModel, CreateUserGroupRequest> {
}
