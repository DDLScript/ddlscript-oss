package com.ddlscript.repository.embedded.rowdata;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserGroupRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("ID")
	private int id;

	@JsonSetter("NAME")
	private String name;

	@JsonSetter("DESCRIPTION")
	private String description;

	@JsonSetter("USER_ID_CREATED")
	private Integer userIdCreated;
}
