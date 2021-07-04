package com.ddlscript.app.schema.projects.scripts;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.templates.scripts.ScriptTemplateSummarizedSchema;
import com.ddlscript.def.projects.scripts.ScriptModel;
import com.ddlscript.def.templates.scripts.FilterScriptTemplateRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class ScriptSchema extends ScriptSummarizedSchema {

	public ScriptSchema(
			@NonNull final AuthenticationContext withAuthenticationContext
			, @NonNull final ScriptModel withModel
	) {
		super(withAuthenticationContext, withModel);
	}

	@JsonProperty("templates")
	public Collection<ScriptTemplateSummarizedSchema> getTemplates() {
		var project = ControllerFactory.INSTANCE
				.getProjectController()
				.find(this.getModel()
						.getProjectIdentifier())
				.orElseThrow();
		var filter = FilterScriptTemplateRequest.builder()
				.setProject(project)
				.setScript(this.getModel())
				.build();
		return ControllerFactory.INSTANCE
				.getScriptTemplateController()
				.filter(filter)
				.getElements()
				.stream()
				.map(element -> new ScriptTemplateSummarizedSchema(this.getAuthenticationContext(), element))
				.collect(Collectors.toList());
	}
}
