package com.ddlscript.repository.embedded.rowdata;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationRowData implements RowData {

	@JsonSetter("ID")
	private Integer id;

	@JsonSetter("PASSWORD_HASH")
	private String passwordHash;
}
