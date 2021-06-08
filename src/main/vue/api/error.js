'use strict';

/**
 * DDLScriptError is the base error from which all other more specific DDL Script errors derive.
 * Specifically for errors returned from DDL Script's REST API.
 */
class DDLScriptError extends Error {

	constructor(raw = {}) {
		super(raw.message);
		this.raw = raw;
		this.type = this.constructor.name;

		this.rawType = raw.type;
		this.code = raw.code;
		this.param = raw.param;
		this.detail = raw.detail;
		this.headers = raw.headers;
		this.requestId = raw.requestId;
		this.statusCode = raw.statusCode;
		this.message = raw.message;
		this.source = raw.source;
	}
}


/**
 * InvalidRequestError is raised when a request is initiated with invalid
 * parameters.
 */
class DDLScriptInvalidRequestError extends DDLScriptError {}

/**
 * APIError is a generic error that may be raised in cases where none of the
 * other named errors cover the problem. It could also be raised in the case
 * that a new error has been introduced in the API, but this version of the
 * Node.JS SDK doesn't know how to handle it.
 */
class DDLScriptAPIError extends DDLScriptError {}

/**
 * AuthenticationError is raised when invalid credentials are used to connect
 * to DDLScript's servers.
 */
class DDLScriptAuthenticationError extends DDLScriptError {}

/**
 * PermissionError is raised in cases where access was attempted on a resource
 * that wasn't allowed.
 */
class DDLScriptPermissionError extends DDLScriptError {}

/**
 * RateLimitError is raised in cases where an account is putting too much load
 * on DDLScript's API servers (usually by performing too many requests). Please
 * back off on request rate.
 */
class DDLScriptRateLimitError extends DDLScriptError {}

/**
 * DDLScriptConnectionError is raised in the event that the SDK can't connect to
 * DDLScript's servers. That can be for a variety of different reasons from a
 * downed network to a bad TLS certificate.
 */
class DDLScriptConnectionError extends DDLScriptError {}

/**
 * DDLScriptUnprocessableRequestError is raised when the provided content cannot be
 * processed by the server, usually the given data has failed validation checks.
 */
class DDLScriptUnprocessableRequestError extends DDLScriptError {}


module.exports.DDLScriptError = DDLScriptError;
module.exports.DDLScriptInvalidRequestError = DDLScriptInvalidRequestError;
module.exports.DDLScriptAPIError = DDLScriptAPIError;
module.exports.DDLScriptAuthenticationError = DDLScriptAuthenticationError;
module.exports.DDLScriptPermissionError = DDLScriptPermissionError;
module.exports.DDLScriptRateLimitError = DDLScriptRateLimitError;
module.exports.DDLScriptConnectionError = DDLScriptConnectionError;
module.exports.DDLScriptUnprocessableRequestError = DDLScriptUnprocessableRequestError;
