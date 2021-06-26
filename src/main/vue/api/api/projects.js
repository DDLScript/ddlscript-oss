
import _scripts from './projects/scripts';

export default (DDLScript) => ({

	scripts: _scripts(DDLScript),

	list: async () => await DDLScript.get(
		"/projects"
	),

	get: async (project_id) => await DDLScript.get(
		"/projects/" + project_id
	),

	create: async (payload) => await DDLScript.post(
		"/projects"
		, {}
		, {
			name: payload.name
		}
	),

	update: async (project_id, payload) => await DDLScript.patch(
		"/projects/" + project_id
		, {}
		, {
			name: payload.name
		}
	),

});