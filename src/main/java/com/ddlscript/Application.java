package com.ddlscript;

import com.ddlscript.factories.DataSourceFactory;
import com.ddlscript.repository.embedded.EmbeddedDatabaseMutator;
import com.ddlscript.routegroups.ApiRouteGroup;
import com.ddlscript.routes.ApplicationRoute;
import lombok.experimental.UtilityClass;
import spark.Spark;

import java.util.logging.Logger;

@UtilityClass
public class Application {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	/**
	 * Main entry point for the application.
	 *
	 * @param args
	 * 		Command Line Arguments
	 */
	public void main(String... args) {
		LOGGER.info("Starting DDL Script Webserver");

		EmbeddedDatabaseMutator.update(DataSourceFactory.INSTANCE.getDataSource());

		Application.initializeRoutes();
	}

	/**
	 * Set up the Spark routes.
	 */
	private void initializeRoutes() {
		Spark.staticFiles.location("/webroot");

		Spark.exception(Exception.class, (exception, request, response) -> {
			exception.printStackTrace();
		});

		Spark.path("/api", new ApiRouteGroup());

		// default application - routing is handled on the browser-side
		// this MUST be the last route defined
		Spark.get("/*", new ApplicationRoute());

		// Log the port number
		Logger.getGlobal()
				.info(String.join(System.lineSeparator()
						, "Application is running on port: " + Spark.port()
						, "\tRest API Documentation: http://localhost:" + Spark.port() + "/"
				));
	}

}
