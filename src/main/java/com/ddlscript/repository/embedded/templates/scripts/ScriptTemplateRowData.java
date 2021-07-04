package com.ddlscript.repository.embedded.templates.scripts;

import com.ddlscript.repository.embedded.RowData;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@ToString
public class ScriptTemplateRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("TIMESTAMP_UPDATED")
	private Timestamp timestampUpdated;

	@JsonSetter("PROJECT_ID")
	private int projectId;

	@JsonSetter("SCRIPT_ID")
	private Integer scriptId;

	@JsonSetter("APPLIED_TO_EACH_STATEMENT")
	private boolean appliedToEachStatement;

	@JsonSetter("APPLIED_AFTER")
	private boolean appliedAfter;

	@JsonSetter("QUERY")
	private String query;

	@JsonSetter("USER_ID_CREATED")
	private Integer userIdCreated;

	@JsonSetter("USER_ID_UPDATED")
	private Integer userIdUpdated;

	public Optional<Timestamp> optTimestampUpdated() {
		return Optional.ofNullable(this.getTimestampUpdated());
	}

	public Optional<Integer> optScriptId() {
		return Optional.ofNullable(this.getScriptId());
	}

	public Optional<Integer> optUserIdUpdated() {
		return Optional.ofNullable(this.getUserIdUpdated());
	}
}
