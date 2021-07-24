package com.ddlscript.app.schema.projects;

import com.ddlscript.app.schema.common.TimestampCreatedSchema;
import com.ddlscript.def.projects.ProjectModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

public class ProjectSummarizedSchema implements TimestampCreatedSchema {

	@JsonIgnore
	@Getter
	private final ProjectModel model;

	public ProjectSummarizedSchema(@NonNull final ProjectModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("id")
	public int getId() {
		return this.model.getIdentifier()
				.getRawValue();
	}

	@JsonProperty("name")
	public String getName() {
		return this.model.getName();
	}

	@JsonProperty("type")
	public String getDatabaseType() {
		return this.model.getDatabaseType();
	}

}
