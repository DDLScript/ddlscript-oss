package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.usergroups.UserGroupModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.UserGroupRowData;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString
public class EmbeddedDatabaseUserGroupModel
		extends AbstractEmbeddedDatabaseModel<UserGroupRowData>
		implements UserGroupModel {

	public EmbeddedDatabaseUserGroupModel(@NonNull final UserGroupRowData withRowData) {
		super(withRowData);
	}

	@Override
	public UserGroupIdentifier getIdentifier() {
		return new UserGroupIdentifier(this.getRowData()
				.getId());
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public String getName() {
		return this.getRowData()
				.getName();
	}

	@Override
	public String getDescription() {
		return this.getRowData()
				.getDescription();
	}
}
