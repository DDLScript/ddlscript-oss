
import _scripts from './projects/scripts';

export default (DDLScript) => ({

	scripts: _scripts(DDLScript),

	list: async () => await DDLScript.get(
		"/projects"
	),

	get: async (project_id) => await DDLScript.get(
		"/projects/" + project_id
	),

	delete: async (project_id) => await DDLScript.delete(
		"/projects/" + project_id
	),

	create: async (payload) => await DDLScript.post(
		"/projects"
		, {}
		, {
			name: payload.name
		}
	),

	update: async (project_id, payload) => {
		const _payload = {};

		if (payload.name) {
			_payload.name = payload.name;
		}

		if (payload.templates) {
			_payload.templates = payload.templates;
		}

		return await DDLScript.patch(
			"/projects/" + project_id
			, {}
			, _payload
		);
	},

});