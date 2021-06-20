package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.ProjectPermissionController;
import com.ddlscript.def.models.permissions.project.FilterProjectPermissionRequest;
import com.ddlscript.def.models.permissions.project.ProjectPermission;
import com.ddlscript.repository.embedded.rowdata.ProjectPermissionRowData;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.stream.Collectors;

public class EmbeddedDatabaseProjectPermissionController
		extends AbstractEmbeddedDatabaseController
		implements ProjectPermissionController {

	private static final String SQL_FILTER = ResourceUtils.getResourceAsString(
			"/database/dml/permissions/project/filter.sql");

	public EmbeddedDatabaseProjectPermissionController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	@Override
	public PaginatedCollection<ProjectPermission> filter(
			final FilterProjectPermissionRequest withRequest
	) {
		var rowData = super.filter(
				ProjectPermissionRowData.class
				, SQL_FILTER
				, withRequest.getProject()
						.getIdentifier()
						.getRawValue()
				, withRequest.getAccessibleToUser()
						.getIdentifier()
						.getRawValue()
		)
				.stream()
				.map(ProjectPermissionRowData::getId)
				.map(ProjectPermission::ofIdentifier)
				.collect(Collectors.toList());

		return PaginatedCollection.<ProjectPermission>builder()
				.setElements(rowData)
				.setPageSize(Integer.MAX_VALUE)
				.setPageCount(1)
				.build();
	}
}
