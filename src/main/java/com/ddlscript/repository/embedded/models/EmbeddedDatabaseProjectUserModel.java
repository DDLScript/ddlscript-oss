package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.users.ProjectUserModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.ProjectUserRowData;
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
