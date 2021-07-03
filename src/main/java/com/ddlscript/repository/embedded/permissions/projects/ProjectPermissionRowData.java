package com.ddlscript.repository.embedded.permissions.projects;

import com.ddlscript.repository.embedded.RowData;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectPermissionRowData implements RowData {
	@JsonSetter("ID")
	private Integer id;

	@JsonSetter("NAME")
	private String name;
}
