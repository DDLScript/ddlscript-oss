package com.ddlscript.app.schema.common;

import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.schema.users.UserSummarizedSchema;
import com.ddlscript.def.common.AuditedModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface AuditedSchema extends TimestampCreatedSchema {

	/**
	 * Fetches an instance of the underlying model.
	 *
	 * @return Audited Model.
	 */
	@JsonIgnore
	AuditedModel getModel();

	@JsonProperty("created_by")
	default UserSummarizedSchema getCreatedUserSummarizedSchema() {
		var createdIdentifier = this.getModel()
				.getCreatedUserIdentifier();

		return ControllerFactory.INSTANCE
				.getUserController()
				.find(createdIdentifier)
				.map(UserSummarizedSchema::new)
				.orElseThrow();
	}
}
