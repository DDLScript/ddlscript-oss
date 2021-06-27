package com.ddlscript.app.inputs.sessions;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateSessionInput {

	@JsonSetter("username")
	private String username;

	@JsonSetter("password")
	private String password;

	@JsonSetter("remember_me")
	private boolean rememberMe;
}
