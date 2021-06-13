package com.ddlscript.schema.projects;

import com.ddlscript.def.models.projects.ProjectModel;
import lombok.NonNull;

public class ProjectSchema extends ProjectSummarizedSchema {

	public ProjectSchema(@NonNull final ProjectModel withModel) {
		super(withModel);
	}

}
