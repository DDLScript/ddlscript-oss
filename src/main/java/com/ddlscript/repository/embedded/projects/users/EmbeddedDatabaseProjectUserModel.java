package com.ddlscript.repository.embedded.projects.users;

import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.users.ProjectUserModel;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseModel;
import lombok.NonNull;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseProjectUserModel
		extends AbstractEmbeddedDatabaseModel<ProjectUserRowData>
		implements ProjectUserModel {

	public EmbeddedDatabaseProjectUserModel(@NonNull final ProjectUserRowData withRowData) {
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
