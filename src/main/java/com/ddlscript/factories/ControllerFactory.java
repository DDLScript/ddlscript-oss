package com.ddlscript.factories;

import com.ddlscript.def.controllers.ProjectController;
import com.ddlscript.def.controllers.ProjectPermissionController;
import com.ddlscript.def.controllers.SessionController;
import com.ddlscript.def.controllers.SystemPermissionController;
import com.ddlscript.def.controllers.UserController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseProjectController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseProjectPermissionController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseSessionController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseSystemPermissionController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseUserController;
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
	private final SessionController sessionController = new EmbeddedDatabaseSessionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final SystemPermissionController systemPermissionController = new EmbeddedDatabaseSystemPermissionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final UserController userController = new EmbeddedDatabaseUserController(DataSourceFactory.INSTANCE.getDataSource());
}
