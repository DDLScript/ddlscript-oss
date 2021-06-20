package com.ddlscript.repository.embedded.models;

import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.scripts.ScriptIdentifier;
import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.ScriptRowData;
import lombok.NonNull;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseScriptModel
		extends AbstractEmbeddedDatabaseModel<ScriptRowData>
		implements ScriptModel {

	public EmbeddedDatabaseScriptModel(@NonNull final ScriptRowData withRowData) {
		super(withRowData);
	}

	@Override
	public ScriptIdentifier getIdentifier() {
		return new ScriptIdentifier(this.getRowData()
				.getId());
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public Instant getTimestampUpdated() {
		return this.optRowData()
				.map(ScriptRowData::getTimestampUpdated)
				.map(Timestamp::toInstant)
				.orElse(null);
	}

	@Override
	public String getTitle() {
		return this.getRowData()
				.getTitle();
	}

	@Override
	public String getDescription() {
		return this.getRowData()
				.getDescription();
	}

	@Override
	public ProjectIdentifier getProjectIdentifier() {
		return new ProjectIdentifier(this.getRowData()
				.getProjectId());
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}

	@Override
	public UserIdentifier getUpdatedUserIdentifier() {
		return this.optRowData()
				.map(ScriptRowData::getUserIdUpdated)
				.map(UserIdentifier::new)
				.orElse(null);
	}
}
