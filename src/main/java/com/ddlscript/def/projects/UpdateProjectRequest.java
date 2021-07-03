package com.ddlscript.def.projects;

import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.models.UpdatableRequest;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class UpdateProjectRequest implements UpdatableRequest<ProjectModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Display name to assign to the project.
	 */
	@Getter
	private String name;

	/**
	 * User whom is updating the project.
	 */
	@Getter
	@Setter
	@NonNull
	private UserModel userUpdated;

	/**
	 * Default Constructor.
	 */
	private UpdateProjectRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private UpdateProjectRequest(@NonNull UpdateProjectRequest other) {
		this.setName(other.getName());
		this.setUserUpdated(other.getUserUpdated());
	}

	private void setName(@NonNull final String withName) {
		this.name = StringParser.of(withName)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(ProjectModel.MAX_NAME_LENGTH)
				.orElseThrow();
	}

	public static class Builder
			implements
			UpdatableRequestBuilder<ProjectModel, UpdateProjectRequest, Builder> {

		private final UpdateProjectRequest request = new UpdateProjectRequest();

		public Builder setName(@NonNull final String withName) {
			this.request.setName(withName);
			return this;
		}

		public Builder setUserUpdated(@NonNull final UserModel withModel) {
			this.request.setUserUpdated(withModel);
			return this;
		}

		@Override
		public UpdateProjectRequest build() {
			return new UpdateProjectRequest(this.request);
		}
	}
}
