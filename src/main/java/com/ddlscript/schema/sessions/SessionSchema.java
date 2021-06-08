package com.ddlscript.schema.sessions;

import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.schema.users.UserSummarizedSchema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

/**
 * Full schema of a session.
 */
public class SessionSchema {

	@JsonIgnore
	private final SessionModel model;

	public SessionSchema(@NonNull final SessionModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("timestamp_created")
	public String getTimestampCreated() {
		return this.model.getTimestampCreated().toString();
	}

	@JsonProperty("user")
	public UserSummarizedSchema getUser() {
		return ControllerFactory.INSTANCE
				.getUserController()
				.find(this.model.getUserIdentifier())
				.map(UserSummarizedSchema::new)
				.orElse(null);
	}
}
