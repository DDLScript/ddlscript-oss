package com.ddlscript.def.projects.scripts;

import com.ddlscript.def.projects.ProjectModel;
import com.ddlscript.sdk.models.FilterableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request to retrieve a list of scripts.
 */
public class FilterScriptRequest implements FilterableRequest<ScriptModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static FilterScriptRequestBuilder builder() {
		return new FilterScriptRequestBuilder();
	}

	/**
	 * User whom the projects must be accessible to.
	 */
	@Getter
	@Setter
	@NonNull
	private ProjectModel project;

	/**
	 * Default Constructor.
	 */
	private FilterScriptRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private FilterScriptRequest(@NonNull FilterScriptRequest other) {
		this.setProject(other.getProject());
	}

	public static class FilterScriptRequestBuilder
			implements
			FilterableRequestBuilder<ScriptModel, FilterScriptRequest, FilterScriptRequestBuilder> {

		private final FilterScriptRequest request = new FilterScriptRequest();

		public FilterScriptRequestBuilder setProject(@NonNull final ProjectModel withProjectModel) {
			this.request.setProject(withProjectModel);
			return this;
		}

		@Override
		public FilterScriptRequest build() {
			return new FilterScriptRequest(this.request);
		}
	}
}
