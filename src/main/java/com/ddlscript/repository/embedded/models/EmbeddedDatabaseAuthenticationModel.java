package com.ddlscript.repository.embedded.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ddlscript.def.models.authentication.AuthenticationModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.repository.embedded.rowdata.AuthenticationRowData;
import lombok.NonNull;
import lombok.ToString;

import java.nio.charset.StandardCharsets;

@ToString(callSuper = true)
public class EmbeddedDatabaseAuthenticationModel extends AbstractEmbeddedDatabaseModel<AuthenticationRowData>
		implements AuthenticationModel {

	public EmbeddedDatabaseAuthenticationModel(@NonNull final AuthenticationRowData withRowData) {
		super(withRowData);
	}


	@Override
	public UserIdentifier getUserIdentifier() {
		return new UserIdentifier(this.getRowData()
				.getId());
	}

	@Override
	public boolean isValidPassword(final String withPassword) {
		return BCrypt.verifyer()
				.verify(
						withPassword.toCharArray()
						, this.getRowData()
								.getPasswordHash()
								.getBytes(StandardCharsets.UTF_8)
				)
				.verified;
	}
}
