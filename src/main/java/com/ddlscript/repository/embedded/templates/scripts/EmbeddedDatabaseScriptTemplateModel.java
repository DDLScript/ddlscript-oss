package com.ddlscript.repository.embedded.templates.scripts;

import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.scripts.ScriptIdentifier;
import com.ddlscript.def.templates.scripts.ScriptTemplateModel;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseModel;
import lombok.NonNull;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseScriptTemplateModel
		extends AbstractEmbeddedDatabaseModel<ScriptTemplateRowData>
		implements ScriptTemplateModel {

	public EmbeddedDatabaseScriptTemplateModel(@NonNull final ScriptTemplateRowData withRowData) {
		super(withRowData);
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public Instant getTimestampUpdated() {
		return this.getRowData()
				.optTimestampUpdated()
				.map(Timestamp::toInstant)
				.orElse(null);
	}

	@Override
	public ProjectIdentifier getProjectIdentifier() {
		return new ProjectIdentifier(this.getRowData()
				.getProjectId());
	}

	@Override
	public ScriptIdentifier getScriptIdentifier() {
		return this.getRowData()
				.optScriptId()
				.map(ScriptIdentifier::new)
				.orElse(null);
	}

	@Override
	public boolean isAppliedToEachStatement() {
		return this.getRowData()
				.isAppliedToEachStatement();
	}

	@Override
	public boolean isAppliedAfter() {
		return this.getRowData()
				.isAppliedAfter();
	}

	@Override
	public String getQuery() {
		return this.getRowData()
				.getQuery();
	}

	@Override
	public UserIdentifier getCreatedUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getUserIdCreated());
	}

	@Override
	public UserIdentifier getUpdatedUserIdentifier() {
		return this.getRowData()
				.optUserIdUpdated()
				.map(UserIdentifier::new)
				.orElse(null);
	}
}
