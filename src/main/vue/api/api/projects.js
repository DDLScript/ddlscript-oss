
import _scripts from './projects/scripts';

export default (DDLScript) => ({

	scripts: _scripts(DDLScript),

	list: async () => await DDLScript.get(
		"/projects"
	),

	create: async (payload) => await DDLScript.post(
		"/projects"
		, {}
		, {
			name: payload.name
		}
	),

	get: async (id) => await DDLScript.get(
		"/projects/" + id
	),

});