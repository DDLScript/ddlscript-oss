'use strict';

import api from "./api";

//const operations = require('./operations');
const utils = require('./utils');


DDLScript.DEFAULT_PROTOCOL = "https";
DDLScript.DEFAULT_HOST = "ddlscript.com";
DDLScript.DEFAULT_PORT = '443';
DDLScript.DEFAULT_BASE_PATH = '';

DDLScript.PACKAGE_VERSION = require('../../../../package.json').version;

DDLScript.USER_AGENT = {
	bindings_version: DDLScript.PACKAGE_VERSION,
	lang: 'node',
	lang_version: process.version,
	platform: process.platform,
	publisher: 'DDLScript'
};

const ALLOWED_CONFIG_PROPERTIES = [
	'httpAgent'
	, 'protocol'
	, 'host'
	, 'port'
	, 'withCredentials'
	, 'basePath'
	, 'publicPersonalToken'
	, 'secretSigningKey'
];

function DDLScript(config = {}) {
	if (!(this instanceof DDLScript)) {
		return new DDLScript(config);
	}

	const props = this._getPropsFromConfig(config);

	this._api = {
		auth: null
		, protocol: props.protocol || DDLScript.DEFAULT_PROTOCOL
		, host: props.host || DDLScript.DEFAULT_HOST
		, port: props.port || DDLScript.DEFAULT_PORT
		, basePath: props.basePath || DDLScript.DEFAULT_BASE_PATH
		, agent: props.httpAgent || null
		, dev: false
		, withCredentials: props.withCredentials || false
		, publicPersonalToken: props.publicPersonalToken || null
		, secretSigningKey: props.secretSigningKey || null
	};

	this.errors = require('./error');
	this.utils = utils;
	this.httpRequest = require('./httpRequest')(this);

	this.post = this.httpRequest.post;
	this.put = this.httpRequest.put;
	this.patch = this.httpRequest.patch;
	this.delete = this.httpRequest.delete;
	this.get = this.httpRequest.get;
	this.head = this.httpRequest.head;

	this.api = api(this);

	// copy across the rest endpoints
//    const opts = operations(this);
//    for (const name in opts) {
//        this[utils.pascalToCamelCase(name)] = opts[name];
//    }
}

DDLScript.errors = require('./error');

DDLScript.prototype = {

	getApiField(key) {
		return this._api[key];
	},

	_setApiField(key, value) {
		this._api[key] = value;
	},

	_getPropsFromConfig(config) {
		// If config is null or undefined, just bail early with no props
		if (!config) {
			return {};
		}

		// config can be an object or a string
		// const isString = typeof config === 'string';
		const isObject = config === Object(config) && !Array.isArray(config);

		if (!isObject) {
			throw new Error('Config must be an object');
		}

		// If config is an object, we assume the new behavior and make sure it doesn't contain any unexpected values
		const values = Object.keys(config).filter(
			(value) => !ALLOWED_CONFIG_PROPERTIES.includes(value)
		);

		if (values.length > 0) {
			throw new Error(
			`Config object may only contain the following: ${ALLOWED_CONFIG_PROPERTIES.join(
			', '
			)}`
			);
		}

		return config;
	},
};

//module.exports = DDLScript;
//module.exports.DDLScript = DDLScript;

const instance = new DDLScript({
	host: window.location.hostname
	, port: window.location.port
	, protocol: window.location.protocol.replace(":", "")
	, basePath: "/api"
	, withCredentials: true
});
export default instance;