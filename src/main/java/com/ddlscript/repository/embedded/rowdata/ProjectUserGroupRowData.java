package com.ddlscript.repository.embedded.rowdata;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ProjectUserGroupRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("PROJECT_ID")
	private int projectId;

	@JsonSetter("USERGROUP_ID")
	private int userGroupId;

	@JsonSetter("USER_ID_CREATED")
	private int userIdCreated;

}
