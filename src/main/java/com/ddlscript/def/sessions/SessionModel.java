package com.ddlscript.def.sessions;

import com.ddlscript.def.common.AuditedModel;
import com.ddlscript.sdk.Model;

/**
 * Represents a single user session.
 */
public interface SessionModel extends Model, AuditedModel {

	/**
	 * Returns the token identifying this session.
	 *
	 * @return Session Token.
	 */
	String getToken();

}
