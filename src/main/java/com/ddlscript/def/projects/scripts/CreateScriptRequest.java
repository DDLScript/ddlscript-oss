package com.ddlscript.def.projects.scripts;

import com.ddlscript.def.projects.ProjectModel;
import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.models.CreatableRequest;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class CreateScriptRequest implements CreatableRequest<ScriptModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Display name to assign to the group.
	 */
	@Getter
	private String title;

	/**
	 * Project the script is being created within.
	 */
	@Getter
	@Setter
	@NonNull
	private ProjectModel project;

	/**
	 * User whom is creating the new group.
	 */
	@Getter
	@Setter
	@NonNull
	private UserModel userCreated;

	/**
	 * Default Constructor.
	 */
	private CreateScriptRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private CreateScriptRequest(@NonNull CreateScriptRequest other) {
		this.setTitle(other.getTitle());
		this.setUserCreated(other.getUserCreated());
		this.setProject(other.getProject());
	}

	private void setTitle(@NonNull final String withTitle) {
		this.title = StringParser.of(withTitle)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(ScriptModel.MAX_TITLE_LENGTH)
				.orElseThrow();
	}

	public static class Builder
			implements CreatableRequestBuilder<ScriptModel, CreateScriptRequest, Builder> {

		private final CreateScriptRequest request = new CreateScriptRequest();

		public Builder setTitle(@NonNull final String withTitle) {
			this.request.setTitle(withTitle);
			return this;
		}

		public Builder setProject(@NonNull final ProjectModel withModel) {
			this.request.setProject(withModel);
			return this;
		}

		public Builder setUserCreated(@NonNull final UserModel withModel) {
			this.request.setUserCreated(withModel);
			return this;
		}

		@Override
		public CreateScriptRequest build() {
			return new CreateScriptRequest(this.request);
		}
	}
}
