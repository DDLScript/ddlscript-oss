package com.ddlscript.repository.embedded.sessions;

import com.ddlscript.def.sessions.SessionModel;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseModel;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseSessionModel extends AbstractEmbeddedDatabaseModel<SessionRowData>
		implements SessionModel {

	public EmbeddedDatabaseSessionModel(@NonNull final SessionRowData withRowData) {
		super(withRowData);
	}

	@Override
	public String getToken() {
		return this.getRowData()
				.getToken();
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserId());
	}
}
