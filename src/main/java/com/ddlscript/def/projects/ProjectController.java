package com.ddlscript.def.projects;

import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.DeletableController;
import com.ddlscript.sdk.controllers.DescribableController;
import com.ddlscript.sdk.controllers.FilterableController;
import com.ddlscript.sdk.controllers.FindableController;
import com.ddlscript.sdk.controllers.UpdatableController;

/**
 * Controller for managing User Models.
 */
public interface ProjectController extends FindableController<ProjectIdentifier, ProjectModel>
		, CreatableController<ProjectModel, CreateProjectRequest>
		, FilterableController<ProjectModel, FilterProjectRequest>
		, DescribableController<ProjectModel, DescribeProjectRequest>
		, UpdatableController<ProjectModel, UpdateProjectRequest>
		, DeletableController<ProjectModel> {
}
