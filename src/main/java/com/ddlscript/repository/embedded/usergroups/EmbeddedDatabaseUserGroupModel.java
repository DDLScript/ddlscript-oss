package com.ddlscript.repository.embedded.usergroups;

import com.ddlscript.def.usergroups.UserGroupIdentifier;
import com.ddlscript.def.usergroups.UserGroupModel;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseModel;
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
