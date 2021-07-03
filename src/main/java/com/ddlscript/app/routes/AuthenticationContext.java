package com.ddlscript.app.routes;

import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.sessions.SessionModel;
import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.SdkBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;
import java.util.EnumSet;

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

	private final EnumSet<SystemPermission> systemPermissions = EnumSet.noneOf(SystemPermission.class);

	/**
	 * Default Constructor.
	 */
	private AuthenticationContext() { }

	public EnumSet<SystemPermission> getSystemPermissions() {
		return EnumSet.copyOf(this.systemPermissions);
	}

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private AuthenticationContext(@NonNull AuthenticationContext other) {
		this.setSessionModel(other.getSessionModel());
		this.setUserModel(other.getUserModel());
		this.systemPermissions.addAll(other.getSystemPermissions());
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

		public AuthenticationContextBuilder setSystemPermissions(@NonNull final Collection<SystemPermission> withCollection) {
			this.request.systemPermissions.clear();
			this.request.systemPermissions.addAll(withCollection);
			return this;
		}

		@Override
		public AuthenticationContext build() {
			return new AuthenticationContext(this.request);
		}
	}
}
