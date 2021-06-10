


export default (DDLScript) => ({

	list: async () => await DDLScript.get(
		"/projects"
	),

});