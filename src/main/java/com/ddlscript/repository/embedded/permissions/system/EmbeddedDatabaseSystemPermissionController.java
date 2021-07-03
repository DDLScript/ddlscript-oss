package com.ddlscript.repository.embedded.permissions.system;

import com.ddlscript.def.permissions.system.SystemPermissionController;
import com.ddlscript.def.permissions.system.FilterSystemPermissionRequest;
import com.ddlscript.def.permissions.system.SystemPermission;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseController;
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
