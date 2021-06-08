package com.ddlscript.repository.embedded.rowdata;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("TIMESTAMP_ARCHIVED")
	private Timestamp timestampArchived;

	@JsonSetter("ID")
	private int id;

	@JsonSetter("NAME")
	private String name;

	@JsonSetter("USERNAME")
	private String username;

	@JsonSetter("EMAIL")
	private String email;

	@JsonSetter("ADMINISTRATOR")
	private boolean administrator;

	@JsonSetter("PASSWORD_HASH")
	private String passwordHash;
}
