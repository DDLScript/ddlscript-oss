package com.ddlscript.def.users;

import com.ddlscript.sdk.models.CreatableRequest;
import com.ddlscript.utils.StringParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User instance.
 */
public class CreateUserRequest implements CreatableRequest<UserModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static CreateUserRequest.CreateUserRequestBuilder builder() {
		return new CreateUserRequest.CreateUserRequestBuilder();
	}

	/**
	 * Display name to assign to the user.
	 */
	@Getter
	private String name;

	/**
	 * Username to assign to the user.
	 */
	@Getter
	private String username;

	/**
	 * User's email address.
	 */
	@Getter
	private String email;

	/**
	 * Flag indicating if the user is an administrator.
	 */
	@Getter
	@Setter(AccessLevel.PRIVATE)
	private boolean administrator;

	/**
	 * Default Constructor.
	 */
	private CreateUserRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private CreateUserRequest(@NonNull CreateUserRequest other) {
		this.setName(other.getName());
		this.setUsername(other.getUsername());
		this.setEmail(other.getEmail());
		this.setAdministrator(other.isAdministrator());
	}

	private void setName(@NonNull final String withName) {
		this.name = StringParser.of(withName)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserModel.MAX_NAME_LENGTH)
				.orElseThrow();
	}

	private void setUsername(final String withUsername) {
		this.username = StringParser.of(withUsername)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserModel.MAX_USERNAME_LENGTH)
				.toString();
	}

	private void setEmail(final String withEmailAddress) {
		this.email = StringParser.of(withEmailAddress)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserModel.MAX_EMAIL_LENGTH)
				.toString();
	}

	public static class CreateUserRequestBuilder
			implements
			CreatableRequestBuilder<UserModel, CreateUserRequest, CreateUserRequest.CreateUserRequestBuilder> {

		private final CreateUserRequest request = new CreateUserRequest();

		public CreateUserRequest.CreateUserRequestBuilder setName(@NonNull final String withName) {
			this.request.setName(withName);
			return this;
		}

		public CreateUserRequest.CreateUserRequestBuilder setUsername(@NonNull final String withUsername) {
			this.request.setUsername(withUsername);
			return this;
		}

		public CreateUserRequest.CreateUserRequestBuilder setEmail(@NonNull final String withEmail) {
			this.request.setEmail(withEmail);
			return this;
		}

		public CreateUserRequest.CreateUserRequestBuilder setAdministrator(final boolean isAdministrator) {
			this.request.setAdministrator(isAdministrator);
			return this;
		}

		@Override
		public CreateUserRequest build() {
			return new CreateUserRequest(this.request);
		}
	}
}
