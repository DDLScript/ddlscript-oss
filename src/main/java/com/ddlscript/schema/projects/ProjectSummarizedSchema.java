package com.ddlscript.schema.projects;

import com.ddlscript.def.models.projects.ProjectModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class ProjectSummarizedSchema {

	@JsonIgnore
	private final ProjectModel model;

	public ProjectSummarizedSchema(@NonNull final ProjectModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("id")
	public int getId() {
		return this.model.getIdentifier().getRawValue();
	}

	@JsonProperty("name")
	public String getName() {
		return this.model.getName();
	}

	@JsonProperty("type")
	public String getDatabaseType() {
		return this.model.getDatabaseType();
	}

	@JsonProperty("timestamp_created")
	public String getTimestampCreated() {
		return this.model.getTimestampCreated().toString();
	}

}
