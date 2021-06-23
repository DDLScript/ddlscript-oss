package com.ddlscript.schema.projects.scripts;

import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.Optional;

/**
 * Summarized schema for scripts.
 */
public class ScriptSummarizedSchema {

	@JsonIgnore
	@Getter(AccessLevel.PROTECTED)
	private final ScriptModel model;

	public ScriptSummarizedSchema(@NonNull final ScriptModel withModel) {
		this.model = withModel;
	}

	@JsonProperty("id")
	public int getId() {
		return this.model.getIdentifier().getRawValue();
	}

	@JsonProperty("title")
	public String getTitle() {
		return this.model.getTitle();
	}

	@JsonProperty("timestamp_created")
	public String getTimestampCreated() {
		return this.model.getTimestampCreated().toString();
	}

	@JsonProperty("timestamp_updated")
	public String getTimestampUpdated() {
		return Optional.of(this.model)
				.map(ScriptModel::getTimestampUpdated)
				.map(Instant::toString)
				.orElse(null);
	}

}
