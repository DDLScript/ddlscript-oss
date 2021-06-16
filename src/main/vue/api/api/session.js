


export default (DDLScript) => ({

	get: async () => await DDLScript.get(
		"/session"
	),

	post: async (payload) => await DDLScript.post(
		"/session"
		, null
		, {
			username: payload.username,
			password: payload.password
		}
	),

	delete: async () => await DDLScript.delete(
		"/session"
	),


});