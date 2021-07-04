package com.ddlscript.def.templates.scripts;

import com.ddlscript.def.projects.ProjectModel;
import com.ddlscript.def.projects.scripts.ScriptModel;
import com.ddlscript.sdk.models.FilterableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;

/**
 * A request to retrieve a list of script templates.
 */
public class FilterScriptTemplateRequest implements FilterableRequest<ScriptTemplateModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Project the templates must be part of.
	 */
	@Getter
	@Setter
	@NonNull
	private ProjectModel project;

	/**
	 * Script the templates must be part of.
	 */
	@Getter
	@Setter
	private ScriptModel script;

	/**
	 * Returns an optional describing a script.
	 *
	 * @return Optional of a script.
	 */
	public Optional<ScriptModel> optScriptModel() {
		return Optional.ofNullable(this.getScript());
	}

	/**
	 * Default Constructor.
	 */
	private FilterScriptTemplateRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private FilterScriptTemplateRequest(@NonNull FilterScriptTemplateRequest other) {
		this.setProject(other.getProject());
		this.setScript(other.getScript());
	}

	public static class Builder
			implements FilterableRequestBuilder<ScriptTemplateModel, FilterScriptTemplateRequest, Builder> {

		private final FilterScriptTemplateRequest request = new FilterScriptTemplateRequest();

		public Builder setProject(@NonNull final ProjectModel withProjectModel) {
			this.request.setProject(withProjectModel);
			return this;
		}

		public Builder setScript(final ScriptModel withModel) {
			this.request.setScript(withModel);
			return this;
		}

		@Override
		public FilterScriptTemplateRequest build() {
			return new FilterScriptTemplateRequest(this.request);
		}
	}
}
