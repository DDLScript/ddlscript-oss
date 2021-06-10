
import _projects from "./api/projects";
import _session from "./api/session";

export default (DDLScript) => ({

	projects: _projects(DDLScript),
	session: _session(DDLScript),
});