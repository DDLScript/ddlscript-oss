package com.ddlscript.def.usergroups;

import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.FindableController;

/**
 * Controller for managing User Groups.
 */
public interface UserGroupController extends FindableController<UserGroupIdentifier, UserGroupModel>
		, CreatableController<UserGroupModel, CreateUserGroupRequest> {
}
