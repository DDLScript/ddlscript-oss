package com.ddlscript.def.controllers;

import com.ddlscript.def.models.users.CreateUserRequest;
import com.ddlscript.def.models.users.DescribeUserRequest;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.def.models.users.UserModel;
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
