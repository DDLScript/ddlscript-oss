package com.ddlscript.repository.embedded.users;

import com.ddlscript.def.users.UserController;
import com.ddlscript.def.users.CreateUserRequest;
import com.ddlscript.def.users.DescribeUserRequest;
import com.ddlscript.def.users.UserIdentifier;
import com.ddlscript.def.users.UserModel;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseController;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.Optional;

public class EmbeddedDatabaseUserController
		extends AbstractEmbeddedDatabaseController
		implements UserController {

	private static final String SQL_FIND = ResourceUtils.getResourceAsString("/database/dml/users/find.sql");

	private static final String SQL_DESCRIBE = ResourceUtils.getResourceAsString("/database/dml/users/describe.sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString("/database/dml/users/create.sql");

	public EmbeddedDatabaseUserController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<UserModel> find(
			final UserIdentifier withIdentifier
	) {
		var instance = super.describe(
				UserRowData.class
				, SQL_FIND
				, withIdentifier.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseUserModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<UserModel> describe(final DescribeUserRequest withRequest) {
		var instance = super.describe(
				UserRowData.class
				, SQL_DESCRIBE
				, withRequest.getUsername()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseUserModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<UserModel> create(final CreateUserRequest withRequest) {
		var rowData = super.insert(
				UserRowData.class
				, SQL_CREATE
				, withRequest.getName()
				, withRequest.getUsername()
				, withRequest.getEmail()
				, withRequest.isAdministrator()
		);
		return this.find(new UserIdentifier(rowData.getId()));
	}
}
