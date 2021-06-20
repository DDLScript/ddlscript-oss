package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.SystemPermissionController;
import com.ddlscript.def.models.permissions.system.FilterSystemPermissionRequest;
import com.ddlscript.def.models.permissions.system.SystemPermission;
import com.ddlscript.repository.embedded.rowdata.SystemPermissionRowData;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.stream.Collectors;

public class EmbeddedDatabaseSystemPermissionController
		extends AbstractEmbeddedDatabaseController
		implements SystemPermissionController {

	private static final String SQL_FILTER = ResourceUtils.getResourceAsString(
			"/database/dml/permissions/system/filter.sql");

	public EmbeddedDatabaseSystemPermissionController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	@Override
	public PaginatedCollection<SystemPermission> filter(
			final FilterSystemPermissionRequest withRequest
	) {
		var rowData = super.filter(
				SystemPermissionRowData.class
				, SQL_FILTER
				, withRequest.getAccessibleToUser()
						.getIdentifier()
						.getRawValue()
		)
				.stream()
				.map(SystemPermissionRowData::getId)
				.map(SystemPermission::ofIdentifier)
				.collect(Collectors.toList());

		return PaginatedCollection.<SystemPermission>builder()
				.setElements(rowData)
				.setPageSize(Integer.MAX_VALUE)
				.setPageCount(1)
				.build();
	}
}
