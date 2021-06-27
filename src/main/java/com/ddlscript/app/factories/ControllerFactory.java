package com.ddlscript.app.factories;

import com.ddlscript.def.projects.ProjectController;
import com.ddlscript.def.permissions.project.ProjectPermissionController;
import com.ddlscript.def.projects.scripts.ScriptController;
import com.ddlscript.def.sessions.SessionController;
import com.ddlscript.def.permissions.system.SystemPermissionController;
import com.ddlscript.def.users.UserController;
import com.ddlscript.repository.embedded.projects.EmbeddedDatabaseProjectController;
import com.ddlscript.repository.embedded.permissions.projects.EmbeddedDatabaseProjectPermissionController;
import com.ddlscript.repository.embedded.projects.scripts.EmbeddedDatabaseScriptController;
import com.ddlscript.repository.embedded.sessions.EmbeddedDatabaseSessionController;
import com.ddlscript.repository.embedded.permissions.system.EmbeddedDatabaseSystemPermissionController;
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
	private final ProjectController projectController = new EmbeddedDatabaseProjectController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final ProjectPermissionController projectPermissionController = new EmbeddedDatabaseProjectPermissionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final ScriptController scriptController = new EmbeddedDatabaseScriptController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final SessionController sessionController = new EmbeddedDatabaseSessionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final SystemPermissionController systemPermissionController = new EmbeddedDatabaseSystemPermissionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final UserController userController = new EmbeddedDatabaseUserController(DataSourceFactory.INSTANCE.getDataSource());
}
