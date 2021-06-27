package com.ddlscript.repository.embedded.projects.scripts;

import com.ddlscript.repository.embedded.RowData;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ScriptRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("TIMESTAMP_UPDATED")
	private Timestamp timestampUpdated;

	@JsonSetter("ID")
	private int id;

	@JsonSetter("TITLE")
	private String title;

	@JsonSetter("DESCRIPTION")
	private String description;

	@JsonSetter("PROJECT_ID")
	private Integer projectId;

	@JsonSetter("USER_ID_CREATED")
	private Integer userIdCreated;

	@JsonSetter("USER_ID_UPDATED")
	private Integer userIdUpdated;
}
