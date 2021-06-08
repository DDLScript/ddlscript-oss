package com.ddlscript.repository.embedded.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.repository.embedded.rowdata.UserRowData;
import lombok.NonNull;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.Instant;

@ToString(callSuper = true)
public class EmbeddedDatabaseUserModel
		extends AbstractEmbeddedDatabaseModel<UserRowData>
		implements UserModel {

	public EmbeddedDatabaseUserModel(@NonNull final UserRowData withRowData) {
		super(withRowData);
	}

	@Override
	public UserIdentifier getIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getId());
	}

	@Override
	public Instant getTimestampCreated() {
		return this.getRowData()
				.getTimestampCreated()
				.toInstant();
	}

	@Override
	public Instant getTimestampArchived() {
		return this.optRowData()
				.map(UserRowData::getTimestampArchived)
				.map(Timestamp::toInstant)
				.orElse(null);
	}

	@Override
	public String getName() {
		return this.getRowData()
				.getName();
	}

	@Override
	public String getUsername() {
		return this.getRowData()
				.getUsername();
	}

	@Override
	public String getEmail() {
		return this.getRowData()
				.getEmail();
	}

	@Override
	public boolean isAdministrator() {
		return this.getRowData()
				.isAdministrator();
	}

	@Override
	public boolean verifyPassword(@NonNull final String withPassword) {
		var passwordHash = this.getRowData()
				.getPasswordHash();
		if (passwordHash == null) {
			return false;
		}

		BCrypt.verifyer()
				.verify(withPassword.toCharArray(), passwordHash.toCharArray());
		return false;
	}
}
