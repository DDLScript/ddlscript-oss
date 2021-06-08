package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.AuthenticationController;
import com.ddlscript.def.models.authentication.AuthenticationModel;
import com.ddlscript.def.models.authentication.DescribeAuthenticationRequest;
import com.ddlscript.repository.embedded.models.EmbeddedDatabaseAuthenticationModel;
import com.ddlscript.repository.embedded.rowdata.AuthenticationRowData;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.Optional;

public class EmbeddedDatabaseAuthenticationController
		extends AbstractEmbeddedDatabaseController
		implements AuthenticationController {

	private static final String SQL_DESCRIBE = ResourceUtils.getResourceAsString(
			"/database/dml/authentication/describe.sql");

	public EmbeddedDatabaseAuthenticationController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<AuthenticationModel> describe(
			final DescribeAuthenticationRequest withRequest
	) {
		var model = super.describe(
				AuthenticationRowData.class
				, SQL_DESCRIBE
				, withRequest.getUsername()
		);
		return Optional.ofNullable(model)
				.map(EmbeddedDatabaseAuthenticationModel::new);
	}

}
