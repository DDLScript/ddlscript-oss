package com.ddlscript.app.schema.templates.scripts;

import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.def.templates.scripts.ScriptTemplateModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

/**
 * Summarized Schema for script templates.
 */
public class ScriptTemplateSummarizedSchema {

	@JsonIgnore
	@Getter(AccessLevel.PROTECTED)
	private final ScriptTemplateModel model;

	@JsonIgnore
	@Getter(AccessLevel.PROTECTED)
	private final AuthenticationContext authenticationContext;

	public ScriptTemplateSummarizedSchema(@NonNull final AuthenticationContext withAuthenticationContext, @NonNull final ScriptTemplateModel withModel) {
		this.model = withModel;
		this.authenticationContext = withAuthenticationContext;
	}

	@JsonProperty("query")
	public String getQuery() {
		return this.getModel().getQuery();
	}

	@JsonProperty("applied_to_each_statement")
	public boolean isAppliedToEachStatement() {
		return this.getModel().isAppliedToEachStatement();
	}

	@JsonProperty("applied_after")
	public boolean isAppliedAfter() {
		return this.getModel().isAppliedAfter();
	}
}
