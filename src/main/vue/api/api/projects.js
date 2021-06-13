


export default (DDLScript) => ({

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

});