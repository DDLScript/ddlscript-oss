package com.ddlscript.schema.projects.scripts;

import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.ddlscript.routes.AuthenticationContext;
import lombok.NonNull;

public class ScriptSchema extends ScriptSummarizedSchema {

	public ScriptSchema(
			@NonNull final AuthenticationContext withAuthenticationContext, @NonNull final ScriptModel withModel
	) {
		super(withAuthenticationContext, withModel);
	}

}
