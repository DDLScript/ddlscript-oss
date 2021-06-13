package com.ddlscript.routes;

import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.sdk.SdkBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class AuthenticationContext {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static AuthenticationContextBuilder builder() {
		return new AuthenticationContextBuilder();
	}

	/**
	 * Session Model.
	 */
	@Getter
	@Setter(AccessLevel.PRIVATE)
	@NonNull
	private SessionModel sessionModel;

	/**
	 * User model.
	 */
	@Getter
	@Setter(AccessLevel.PRIVATE)
	@NonNull
	private UserModel userModel;

	/**
	 * Default Constructor.
	 */
	private AuthenticationContext() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private AuthenticationContext(@NonNull AuthenticationContext other) {
		this.setSessionModel(other.getSessionModel());
		this.setUserModel(other.getUserModel());
	}

	public static class AuthenticationContextBuilder
			implements SdkBuilder<AuthenticationContext, AuthenticationContextBuilder> {

		private final AuthenticationContext request = new AuthenticationContext();

		public AuthenticationContextBuilder setSessionModel(@NonNull final SessionModel withModel) {
			this.request.setSessionModel(withModel);
			return this;
		}

		public AuthenticationContextBuilder setUserModel(@NonNull final UserModel withModel) {
			this.request.setUserModel(withModel);
			return this;
		}

		@Override
		public AuthenticationContext build() {
			return new AuthenticationContext(this.request);
		}
	}
}
