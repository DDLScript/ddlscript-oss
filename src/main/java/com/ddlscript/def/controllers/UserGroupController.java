package com.ddlscript.def.controllers;

import com.ddlscript.def.models.usergroups.CreateUserGroupRequest;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.usergroups.UserGroupModel;

/**
 * Controller for managing User Groups.
 */
public interface UserGroupController extends FindableController<UserGroupIdentifier, UserGroupModel>
		, CreatableController<UserGroupModel, CreateUserGroupRequest> {
}
