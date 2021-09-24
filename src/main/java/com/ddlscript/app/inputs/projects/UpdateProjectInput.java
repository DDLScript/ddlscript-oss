package com.ddlscript.app.inputs.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	/**
	 * Script Templates to assign to the
	 */
	@JsonSetter("templates")
	private TemplateInput template;

	/**
	 * Template input.
	 */
	@Getter
	@Setter
	@ToString
	public static class TemplateInput {

		/**
		 * SQL Template to appear at the start of each script.
		 */
		@JsonProperty("before_all")
		private String beforeAll;

		/**
		 * SQL Template to appear before each statement on a script.
		 */
		@JsonProperty("before_each")
		private String beforeEach;

		/**
		 * SQL Template to appear after each statement on a string.
		 */
		@JsonProperty("after_each")
		private String afterEach;

		/**
		 * SQL Template to appear at the end of each script.
		 */
		@JsonProperty("after_all")
		private String afterAll;
	}
}
