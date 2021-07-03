package com.ddlscript.repository.embedded.usergroups.members;

import com.ddlscript.def.usergroups.UserGroupIdentifier;
import com.ddlscript.def.usergroups.members.UserGroupMemberModel;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseModel;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseUserGroupMemberModel
		extends AbstractEmbeddedDatabaseModel<UserGroupMemberRowData>
		implements UserGroupMemberModel {

	public EmbeddedDatabaseUserGroupMemberModel(@NonNull final UserGroupMemberRowData withRowData) {
		super(withRowData);
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public UserGroupIdentifier getUserGroupIdentifier() {
		return new UserGroupIdentifier(this.getRowData()
				.getUserGroupId());
	}

	@Override
	public UserIdentifier getUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserId());
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}
}
