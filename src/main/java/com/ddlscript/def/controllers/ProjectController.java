package com.ddlscript.def.controllers;

import com.ddlscript.def.models.projects.CreateProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.ProjectModel;

/**
 * Controller for managing User Models.
 */
public interface ProjectController extends FindableController<ProjectIdentifier, ProjectModel>
		, CreatableController<ProjectModel, CreateProjectRequest> {
}
