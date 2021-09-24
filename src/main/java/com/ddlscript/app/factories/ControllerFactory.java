package com.ddlscript.app.factories;

import com.ddlscript.def.permissions.project.ProjectPermissionController;
import com.ddlscript.def.permissions.system.SystemPermissionController;
import com.ddlscript.def.projects.ProjectController;
import com.ddlscript.def.projects.scripts.ScriptController;
import com.ddlscript.def.sessions.SessionController;
import com.ddlscript.def.users.UserController;
import com.ddlscript.repository.embedded.permissions.projects.EmbeddedDatabaseProjectPermissionController;
import com.ddlscript.repository.embedded.permissions.system.EmbeddedDatabaseSystemPermissionController;
import com.ddlscript.repository.embedded.projects.EmbeddedDatabaseProjectController;
import com.ddlscript.repository.embedded.projects.scripts.EmbeddedDatabaseScriptController;
import com.ddlscript.repository.embedded.sessions.EmbeddedDatabaseSessionController;
import com.ddlscript.repository.embedded.users.EmbeddedDatabaseUserController;
import lombok.Getter;

/**
 * Controller factory.
 */
public enum ControllerFactory {

	/**
	 * Singleton instance.
	 */
	INSTANCE;

	@Getter
	private final ProjectController projectController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseProjectController::new)
			.orElseThrow();

	@Getter
	private final ProjectPermissionController projectPermissionController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseProjectPermissionController::new)
			.orElseThrow();

	@Getter
	private final ScriptController scriptController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseScriptController::new)
			.orElseThrow();

	@Getter
	private final SessionController sessionController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseSessionController::new)
			.orElseThrow();

	@Getter
	private final SystemPermissionController systemPermissionController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseSystemPermissionController::new)
			.orElseThrow();

	@Getter
	private final UserController userController = DataSourceFactory.INSTANCE
			.optDataSource()
			.map(EmbeddedDatabaseUserController::new)
			.orElseThrow();
}
