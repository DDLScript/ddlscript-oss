package com.ddlscript.schema.projects.scripts;

import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.ddlscript.routes.AuthenticationContext;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

public class ScriptSchema extends ScriptSummarizedSchema {

	@JsonIgnore
	private final AuthenticationContext authenticationContext;

	public ScriptSchema(
			@NonNull final AuthenticationContext withAuthenticationContext, @NonNull final ScriptModel withModel
	) {
		super(withModel);
		this.authenticationContext = withAuthenticationContext;
	}

}
