


export default (DDLScript) => ({

	post: async (payload) => await DDLScript.post(
		"/session"
		, null
		, {
			username: payload.username,
			password: payload.password
		}
	)


});