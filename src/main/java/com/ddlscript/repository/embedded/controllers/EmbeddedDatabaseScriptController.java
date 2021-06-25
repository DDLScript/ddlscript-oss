package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.ScriptController;
import com.ddlscript.def.models.projects.scripts.CreateScriptRequest;
import com.ddlscript.def.models.projects.scripts.FilterScriptRequest;
import com.ddlscript.def.models.projects.scripts.ScriptIdentifier;
import com.ddlscript.def.models.projects.scripts.ScriptModel;
import com.ddlscript.repository.embedded.models.EmbeddedDatabaseScriptModel;
import com.ddlscript.repository.embedded.rowdata.ScriptRowData;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmbeddedDatabaseScriptController
		extends AbstractEmbeddedDatabaseController
		implements ScriptController {

	private static final String SQL_FILTER = ResourceUtils.getResourceAsString(
			"/database/dml/projects/scripts/filter.sql");

	private static final String SQL_FIND = ResourceUtils.getResourceAsString("/database/dml/projects/scripts/find"
			+ ".sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString(
			"/database/dml/projects/scripts/create.sql");

	public EmbeddedDatabaseScriptController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public PaginatedCollection<ScriptModel> filter(@NonNull final FilterScriptRequest withRequest) {
		List<ScriptModel> rowData = super.filter(
				ScriptRowData.class
				, SQL_FILTER
				, withRequest.getProject()
						.getIdentifier()
						.getRawValue()
		)
				.stream()
				.map(EmbeddedDatabaseScriptModel::new)
				.collect(Collectors.toList());

		return PaginatedCollection.<ScriptModel>builder()
				.setElements(rowData)
				.setPageSize(Integer.MAX_VALUE)
				.setPageCount(1)
				.build();
	}

	/**
	 * {@inheritDoc}.
	 */
//	@Override
	public Optional<ScriptModel> find(
			final ScriptIdentifier withIdentifier
	) {
		var instance = super.describe(
				ScriptRowData.class
				, SQL_FIND
				, withIdentifier.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseScriptModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ScriptModel> create(final CreateScriptRequest withRequest) {
		var rowData = super.insert(
				ScriptRowData.class
				, SQL_CREATE
				, withRequest.getProject()
						.getIdentifier()
						.getRawValue()
				, withRequest.getTitle()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);
		var identifier = new ScriptIdentifier(rowData.getId());

		return this.find(identifier);
	}
}
