package com.ddlscript.def.controllers;

import com.ddlscript.def.models.projects.scripts.CreateScriptRequest;
import com.ddlscript.def.models.projects.scripts.FilterScriptRequest;
import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.FilterableController;

/**
 * Controller for managing User Models.
 */
public interface ScriptController extends FilterableController<ScriptModel, FilterScriptRequest>
		, CreatableController<ScriptModel, CreateScriptRequest> {
}
