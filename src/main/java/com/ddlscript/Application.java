package com.ddlscript;

import com.ddlscript.factories.DataSourceFactory;
import com.ddlscript.repository.embedded.EmbeddedDatabaseMutator;
import com.ddlscript.routes.ApplicationRoute;
import com.ddlscript.routes.api.sessions.DeleteSessionRoute;
import com.ddlscript.routes.api.sessions.GetSessionRoute;
import com.ddlscript.routes.api.sessions.PostSessionRoute;
import lombok.experimental.UtilityClass;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;
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

		initializeApiRoutes();

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

	private void initializeApiRoutes() {
		Spark.path("/api", () -> {
			Spark.path("/session", () -> {
				Spark.get("", new GetSessionRoute());
				Spark.post("", new PostSessionRoute());
				Spark.delete("", new DeleteSessionRoute());
			});


			// Default API endpoint
			Route apiNotFound = (request, response) -> {
				response.status(HttpStatus.NOT_FOUND_404);
				return "{\"error\":\"not-found\"}";
			};
			Spark.get("/*", apiNotFound);
			Spark.post("/*", apiNotFound);
			Spark.put("/*", apiNotFound);
			Spark.patch("/*", apiNotFound);
			Spark.delete("/*", apiNotFound);
			Spark.head("/*", apiNotFound);
		});
	}
}
