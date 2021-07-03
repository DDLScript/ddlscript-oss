package com.ddlscript.repository.embedded.sessions;

import com.ddlscript.def.sessions.SessionController;
import com.ddlscript.def.sessions.CreateSessionRequest;
import com.ddlscript.def.sessions.DescribeSessionRequest;
import com.ddlscript.def.sessions.SessionModel;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseController;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;

import javax.sql.DataSource;
import java.util.Optional;

public class EmbeddedDatabaseSessionController
		extends AbstractEmbeddedDatabaseController
		implements SessionController {

	private static final String SQL_DELETE = ResourceUtils.getResourceAsString("/database/dml/sessions/delete.sql");

	private static final String SQL_DESCRIBE = ResourceUtils.getResourceAsString("/database/dml/sessions/describe.sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString("/database/dml/sessions/create.sql");

	public EmbeddedDatabaseSessionController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<SessionModel> describe(
			final DescribeSessionRequest withRequest
	) {
		var model = super.describe(
				SessionRowData.class
				, SQL_DESCRIBE
				, withRequest.getToken()
		);
		return Optional.ofNullable(model).map(EmbeddedDatabaseSessionModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<SessionModel> create(final CreateSessionRequest withRequest) {
		final String token = RandomStringUtils.randomAlphanumeric(128);
		super.insert(
				SessionRowData.class
				, SQL_CREATE
				, token
				, withRequest.getUser()
						.getIdentifier()
						.getRawValue()
		);
		return this.describe(builder -> builder.setToken(token));
	}

	@Override
	public void delete(final SessionModel withModel) {
		super.update(SQL_DELETE, withModel.getToken());
	}
}
