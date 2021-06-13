package com.ddlscript.inputs.projects;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProjectInput {

	@JsonSetter("name")
	private String name;

}
