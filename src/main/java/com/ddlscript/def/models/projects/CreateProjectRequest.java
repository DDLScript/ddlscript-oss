package com.ddlscript.def.models.projects;

import com.ddlscript.def.models.CreatableRequest;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class CreateProjectRequest implements CreatableRequest<ProjectModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static CreateProjectRequestBuilder builder() {
		return new CreateProjectRequestBuilder();
	}

	/**
	 * Display name to assign to the group.
	 */
	@Getter
	private String name;

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
	private CreateProjectRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private CreateProjectRequest(@NonNull CreateProjectRequest other) {
		this.setName(other.getName());
		this.setUserCreated(other.getUserCreated());
	}

	private void setName(@NonNull final String withName) {
		this.name = StringParser.of(withName)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(ProjectModel.MAX_NAME_LENGTH)
				.orElseThrow();
	}

	public static class CreateProjectRequestBuilder
			implements
			CreatableRequestBuilder<ProjectModel, CreateProjectRequest, CreateProjectRequestBuilder> {

		private final CreateProjectRequest request = new CreateProjectRequest();

		public CreateProjectRequestBuilder setName(@NonNull final String withName) {
			this.request.setName(withName);
			return this;
		}

		public CreateProjectRequestBuilder setUserCreated(@NonNull final UserModel withModel) {
			this.request.setUserCreated(withModel);
			return this;
		}

		@Override
		public CreateProjectRequest build() {
			return new CreateProjectRequest(this.request);
		}
	}
}
