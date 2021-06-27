package com.ddlscript.def.users;

import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.DescribableController;
import com.ddlscript.sdk.controllers.FindableController;

/**
 * Controller for managing User Models.
 */
public interface UserController extends FindableController<UserIdentifier, UserModel>
		, CreatableController<UserModel, CreateUserRequest>
		, DescribableController<UserModel, DescribeUserRequest> {
}
