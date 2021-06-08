package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.SessionRowData;
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
	public UserIdentifier getUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserId());
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}
}
