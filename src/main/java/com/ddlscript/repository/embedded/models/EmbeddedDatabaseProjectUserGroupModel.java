package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.usergroups.ProjectUserGroupModel;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.ProjectUserGroupRowData;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseProjectUserGroupModel
		extends AbstractEmbeddedDatabaseModel<ProjectUserGroupRowData>
		implements ProjectUserGroupModel {

	public EmbeddedDatabaseProjectUserGroupModel(@NonNull final ProjectUserGroupRowData withRowData) {
		super(withRowData);
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public ProjectIdentifier getProjectIdentifier() {
		return new ProjectIdentifier(this.getRowData()
				.getProjectId());
	}

	@Override
	public UserGroupIdentifier getUserGroupIdentifier() {
		return new UserGroupIdentifier(this.getRowData()
				.getUserGroupId());
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}
}
