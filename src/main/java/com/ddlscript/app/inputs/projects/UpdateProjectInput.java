package com.ddlscript.app.inputs.projects;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Input parameters for updating a project.
 */
@Getter
@Setter
@ToString
public class UpdateProjectInput {

	/**
	 * Name to associate to the project.
	 */
	@JsonSetter("name")
	private String name;

}
