package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.ProjectController;
import com.ddlscript.def.models.projects.CreateProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.ProjectModel;
import com.ddlscript.repository.embedded.models.EmbeddedDatabaseProjectModel;
import com.ddlscript.repository.embedded.rowdata.ProjectRowData;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.Optional;

public class EmbeddedDatabaseProjectController
		extends AbstractEmbeddedDatabaseController
		implements ProjectController {

	private static final String SQL_FIND = ResourceUtils.getResourceAsString("/database/dml/projects/find.sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString("/database/dml/projects/create.sql");

	public EmbeddedDatabaseProjectController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> find(
			final ProjectIdentifier withIdentifier
	) {
		var instance = super.describe(
				ProjectRowData.class
				, SQL_FIND
				, withIdentifier.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseProjectModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> create(final CreateProjectRequest withRequest) {
		var rowData = super.insert(
				ProjectRowData.class
				, SQL_CREATE
				, withRequest.getName()
				, null
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);
		return this.find(new ProjectIdentifier(rowData.getId()));
	}
}
