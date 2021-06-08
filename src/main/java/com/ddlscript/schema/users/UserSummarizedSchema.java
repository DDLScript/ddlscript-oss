package com.ddlscript.schema.users;

import com.ddlscript.def.models.users.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

/**
 * Summarized schema of a user.
 */
public class UserSummarizedSchema {
	@JsonIgnore
	private final UserModel model;

	public UserSummarizedSchema(@NonNull final UserModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("timestamp_created")
	public String getTimestampCreated() {
		return this.model.getTimestampCreated().toString();
	}

	@JsonProperty("id")
	public int getIdentifier() {
		return this.model.getIdentifier().getRawValue();
	}

	@JsonProperty("name")
	public String getName() {
		return this.model.getName();
	}

	@JsonProperty("username")
	public String getUsername() {
		return this.model.getUsername();
	}

	@JsonProperty("administrator")
	public boolean isAdministrator() {
		return this.model.isAdministrator();
	}
}
