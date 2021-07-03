package com.ddlscript.app.schema.sessions;

import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.users.UserSummarizedSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import java.util.Collection;

/**
 * Full schema of a session.
 */
public class SessionSchema {

	@JsonIgnore
	private final AuthenticationContext authenticationContext;

	public SessionSchema(@NonNull final AuthenticationContext withAuthenticationContext) {
		this.authenticationContext = withAuthenticationContext;
	}

	@JsonProperty("timestamp_created")
	public String getTimestampCreated() {
		return this.authenticationContext.getSessionModel()
				.getTimestampCreated()
				.toString();
	}

	@JsonProperty("user")
	public UserSummarizedSchema getUser() {
		return new UserSummarizedSchema(this.authenticationContext.getUserModel());
	}

	public Collection<SystemPermission> getSystemPermissions() {
		return this.authenticationContext.getSystemPermissions();
	}
}
