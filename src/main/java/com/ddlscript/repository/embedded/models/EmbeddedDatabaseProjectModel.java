package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.ProjectModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.ProjectRowData;
import lombok.NonNull;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseProjectModel
		extends AbstractEmbeddedDatabaseModel<ProjectRowData>
		implements ProjectModel {

	public EmbeddedDatabaseProjectModel(@NonNull final ProjectRowData withRowData) {
		super(withRowData);
	}

	@Override
	public ProjectIdentifier getIdentifier() {
		return new ProjectIdentifier(this.getRowData()
				.getId());
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public Instant getTimestampDeleted() {
		return this.optRowData()
				.map(ProjectRowData::getTimestampDeleted)
				.map(Timestamp::toInstant)
				.orElse(null);
	}

	@Override
	public String getName() {
		return this.getRowData()
				.getName();
	}

	@Override
	public String getDatabaseType() {
		return this.getRowData()
				.getDatabaseType();
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}
}
