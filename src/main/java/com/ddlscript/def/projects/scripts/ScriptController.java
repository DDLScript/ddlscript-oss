package com.ddlscript.def.projects.scripts;

import com.ddlscript.sdk.controllers.CreatableController;
import com.ddlscript.sdk.controllers.FilterableController;

/**
 * Controller for managing User Models.
 */
public interface ScriptController extends FilterableController<ScriptModel, FilterScriptRequest>
		, CreatableController<ScriptModel, CreateScriptRequest> {
}
