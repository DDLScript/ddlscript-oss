package com.ddlscript.app.schema.projects.scripts;

import com.ddlscript.def.projects.scripts.ScriptModel;
import com.ddlscript.app.routes.AuthenticationContext;
import lombok.NonNull;

public class ScriptSchema extends ScriptSummarizedSchema {

	public ScriptSchema(
			@NonNull final AuthenticationContext withAuthenticationContext, @NonNull final ScriptModel withModel
	) {
		super(withAuthenticationContext, withModel);
	}

}
