
import _session from "./api/session";

export default (DDLScript) => ({

	session: _session(DDLScript)
});