


export default (DDLScript) => ({

	list: async (project_id) => await DDLScript.get(
		"/projects/" + project_id + "/scripts"
	),

});