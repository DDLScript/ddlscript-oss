package com.ddlscript.app.schema.sessions;

import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.common.AuditedSchema;
import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.def.sessions.SessionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import java.util.Collection;

/**
 * Full schema of a session.
 */
public class SessionSchema implements AuditedSchema {

	@JsonIgnore
	private final AuthenticationContext authenticationContext;

	@JsonIgnore
	public SessionModel getModel() {
		return this.authenticationContext.getSessionModel();
	}

	public SessionSchema(@NonNull final AuthenticationContext withAuthenticationContext) {
		this.authenticationContext = withAuthenticationContext;
	}

	public Collection<SystemPermission> getSystemPermissions() {
		return this.authenticationContext.getSystemPermissions();
	}
}
