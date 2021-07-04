package com.ddlscript.repository.embedded.templates.scripts;

import com.ddlscript.def.AbstractPositiveIdentifier;
import com.ddlscript.def.templates.scripts.FilterScriptTemplateRequest;
import com.ddlscript.def.templates.scripts.ScriptTemplateController;
import com.ddlscript.def.templates.scripts.ScriptTemplateModel;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseController;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.sdk.models.IdentifiableModel;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

public class EmbeddedDatabaseScriptTemplateController
		extends AbstractEmbeddedDatabaseController
		implements ScriptTemplateController {

	private static final String SQL_FILTER = ResourceUtils.getResourceAsString(
			"/database/dml/templates/scripts/filter.sql");

	public EmbeddedDatabaseScriptTemplateController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	@Override
	public PaginatedCollection<ScriptTemplateModel> filter(final FilterScriptTemplateRequest withRequest) {
		List<ScriptTemplateModel> rowData = super.filter(
				ScriptTemplateRowData.class
				, SQL_FILTER
				, withRequest.getProject()
						.getIdentifier()
						.getRawValue()
				, withRequest.optScriptModel()
						.map(IdentifiableModel::getIdentifier)
						.map(AbstractPositiveIdentifier::getRawValue)
						.orElse(null)
		)
				.stream()
				.map(EmbeddedDatabaseScriptTemplateModel::new)
				.collect(Collectors.toList());

		return PaginatedCollection.<ScriptTemplateModel>builder()
				.setElements(rowData)
				.setPageSize(Integer.MAX_VALUE)
				.setPageCount(1)
				.build();
	}
}
