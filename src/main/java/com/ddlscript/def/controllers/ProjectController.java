package com.ddlscript.def.controllers;

import com.ddlscript.def.models.projects.CreateProjectRequest;
import com.ddlscript.def.models.projects.DescribeProjectRequest;
import com.ddlscript.def.models.projects.FilterProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.ProjectModel;
import com.ddlscript.def.models.projects.UpdateProjectRequest;
import com.ddlscript.sdk.controllers.CreatableController;
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
		, UpdatableController<ProjectModel, UpdateProjectRequest> {
}
