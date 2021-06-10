package com.ddlscript.def.models.usergroups;

import com.ddlscript.sdk.models.CreatableRequest;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class CreateUserGroupRequest implements CreatableRequest<UserGroupModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static CreateUserGroupRequestBuilder builder() {
		return new CreateUserGroupRequestBuilder();
	}

	/**
	 * Display name to assign to the group.
	 */
	@Getter
	private String name;

	/**
	 * Description of the group.
	 */
	@Getter
	private String description;

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
	private CreateUserGroupRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private CreateUserGroupRequest(@NonNull CreateUserGroupRequest other) {
		this.setName(other.getName());
		this.setDescription(other.getDescription());
		this.setUserCreated(other.getUserCreated());
	}

	private void setName(@NonNull final String withName) {
		this.name = StringParser.of(withName)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserGroupModel.MAX_NAME_LENGTH)
				.orElseThrow();
	}

	private void setDescription(final String withDescription) {
		this.description = StringParser.of(withDescription)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserGroupModel.MAX_DESCRIPTION_LENGTH)
				.toString();
	}

	public static class CreateUserGroupRequestBuilder
			implements
			CreatableRequestBuilder<UserGroupModel, CreateUserGroupRequest, CreateUserGroupRequestBuilder> {

		private final CreateUserGroupRequest request = new CreateUserGroupRequest();

		public CreateUserGroupRequestBuilder setName(@NonNull final String withName) {
			this.request.setName(withName);
			return this;
		}

		public CreateUserGroupRequestBuilder setDescription(@NonNull final String withDescription) {
			this.request.setDescription(withDescription);
			return this;
		}

		public CreateUserGroupRequestBuilder setUserCreated(@NonNull final UserModel withModel) {
			this.request.setUserCreated(withModel);
			return this;
		}

		@Override
		public CreateUserGroupRequest build() {
			return new CreateUserGroupRequest(this.request);
		}
	}
}
