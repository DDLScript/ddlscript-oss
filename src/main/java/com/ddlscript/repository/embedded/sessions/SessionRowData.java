package com.ddlscript.repository.embedded.sessions;

import com.ddlscript.repository.embedded.RowData;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class SessionRowData implements RowData {
	@JsonSetter("TIMESTAMP_CREATED")
	private Timestamp timestampCreated;

	@JsonSetter("USER_ID")
	private Integer userId;

	@JsonSetter("TOKEN")
	private String token;
}
