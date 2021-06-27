package com.ddlscript.app.routegroups;

import com.ddlscript.app.routegroups.api.ProjectsApiRouteGroup;
import com.ddlscript.app.routegroups.api.SessionApiRouteGroup;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;
import spark.RouteGroup;
import spark.Spark;

public class ApiRouteGroup implements RouteGroup {
	@Override
	public void addRoutes() {
		Spark.path("/session", new SessionApiRouteGroup());
		Spark.path("/projects", new ProjectsApiRouteGroup());

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
	}
}
