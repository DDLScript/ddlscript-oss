package com.ddlscript.app.schema.users;

import com.ddlscript.app.schema.common.TimestampCreatedSchema;
import com.ddlscript.def.users.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

/**
 * Summarized schema of a user.
 */
public class UserSummarizedSchema implements TimestampCreatedSchema {
	@JsonIgnore
	@Getter
	private final UserModel model;

	public UserSummarizedSchema(@NonNull final UserModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("id")
	public int getIdentifier() {
		return this.model.getIdentifier()
				.getRawValue();
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
