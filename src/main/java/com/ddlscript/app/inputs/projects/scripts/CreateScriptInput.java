package com.ddlscript.app.inputs.projects.scripts;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateScriptInput {

	@JsonSetter("title")
	private String title;

}
