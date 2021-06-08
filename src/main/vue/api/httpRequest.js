const http = require('http');
const https = require('https');
const Package = require('../../../../package.json');


module.exports = (DDLScript) => {
	/**
	 *  handle the actual sending of the request.
	 */
	const executeRequest = async (method, url, headers, body) => {
		return new Promise((resolve, reject) => {
			const transport = DDLScript.getApiField("protocol") == "http" ? http : https;

			const requestParams = {
				host: url.hostname
				, port: url.port
				, path: url.pathname + url.search
				, method: method
				, headers: headers
				, credentials: DDLScript.getApiField("withCredentials") ? "include" : "omit"
			};

			const req = transport.request(requestParams, (res) => {
				// check for a warning header
				if (Object.prototype.hasOwnProperty.call(res, "Warning")) {
					console.warn(res.headers["Warning"]);
				}
				var bodyData = "";
				res.setEncoding('utf8');
				res.on('data', (chunk) => bodyData += chunk);
				res.on('end', () => {
					switch(res.statusCode) {
						case 204: return resolve({});
						case 400: reject(new DDLScript.errors.DDLScriptInvalidRequestError()); break;
						case 401: reject(new DDLScript.errors.DDLScriptAuthenticationError()); break;
						case 403: reject(new DDLScript.errors.DDLScriptPermissionError()); break;
						case 422: reject(new DDLScript.errors.DDLScriptUnprocessableRequestError()); break;
						case 429: reject(new DDLScript.errors.DDLScriptRateLimitError()); break;
						case 503: reject(new DDLScript.errors.DDLScriptConnectionError()); break;

						default:
							if (res.statusCode < 300) {
								return resolve(JSON.parse(bodyData));
							}

							reject(new DDLScript.errors.DDLScriptAPIError());
							break;
					}
				});
			});

			req.on('error', (e) => reject(new DDLScript.errors.DDLScriptAPIError(e)));
			body && req.write(JSON.stringify(body));
			req.end();

		});

	};

	/**
	 * Return the current timestamp in ISO8601 format without milliseconds.
	 */
	const getISO8601Timestamp = () => {
		const d = new Date();
		d.setMilliseconds(0);
		return d;
	}

	/*
	 * Converts the URI Path into a full URL.
	 */
	const resolveRequestURL = (url) => {
		let _tempUrl = "";
		if (DDLScript.getApiField("host") != null) {
			if (DDLScript.getApiField("protocol") != null) {
				_tempUrl += DDLScript.getApiField("protocol") + ":";
			}

			_tempUrl += "//" + DDLScript.getApiField("host");

			if (DDLScript.getApiField("port") != null) {
				_tempUrl += ":" + DDLScript.getApiField("port");
			}
		}
		if (DDLScript.getApiField("basePath") != null) {
			_tempUrl += DDLScript.getApiField("basePath");
		}
		return new URL(_tempUrl + url);
	}

	/**
	 * Parse and send the given request.
	 */
	const parseRequest = async (method, url, headers, body) => {

		// parse the URL
		const requestURL = resolveRequestURL( url);

		// parse headers
		const requestHeaders = {};
		if (typeof headers !== 'undefined') {
			for (var key in headers) {
				if (Object.prototype.hasOwnProperty.call(headers, key)) {
					requestHeaders[key] = headers[key];
				}
			}
		}

		// custom UserAgent
		requestHeaders["User-Agent"] = Package.name + "/" + Package.version + " (" + Package.homepage + ")";

		// set request timestamp
		const requestTimestamp = getISO8601Timestamp();
		requestHeaders["Date"] = requestTimestamp.toUTCString();

		// override specific headers
		requestHeaders["Accept"] = "application/json";

		if (body) {
			requestHeaders["Content-Type"] = "application/json; charset=UTF-8";
			requestHeaders['Content-Length'] = JSON.stringify(body).length;
		}

		return executeRequest(method, requestURL, requestHeaders, body);
	};

	// expose our methods
	return {
		post: async (url, headers, body) => parseRequest("POST", url, headers, body)
		, put: async (url, headers, body) =>  parseRequest("PUT", url, headers, body)
		, patch: async (url, headers, body) => parseRequest("PATCH", url, headers, body)
		, delete: async (url, headers) => parseRequest("DELETE", url, headers, undefined)
		, get: async (url, headers) => parseRequest("GET", url, headers, undefined)
		, head: async (url, headers) => parseRequest("HEAD", url, headers, undefined)
	}
};
