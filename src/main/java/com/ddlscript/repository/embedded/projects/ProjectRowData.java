package com.ddlscript.repository.embedded.projects;

import com.ddlscript.repository.embedded.RowData;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ProjectRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("TIMESTAMP_DELETED")
	private Timestamp timestampDeleted;

	@JsonSetter("ID")
	private int id;

	@JsonSetter("NAME")
	private String name;

	@JsonSetter("DATABASE_TYPE")
	private String databaseType;

	@JsonSetter("USER_ID_CREATED")
	private Integer userIdCreated;

	@JsonSetter("TEMPLATE_BEFORE_ALL")
	private String templateBeforeAll;

	@JsonSetter("TEMPLATE_BEFORE_EACH")
	private String templateBeforeEach;

	@JsonSetter("TEMPLATE_AFTER_EACH")
	private String templateAfterEach;

	@JsonSetter("TEMPLATE_AFTER_ALL")
	private String templateAfterAll;
}
