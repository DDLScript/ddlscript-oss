package com.ddlscript.routegroups.api;

import com.ddlscript.routes.api.projects.GetProjectRoute;
import com.ddlscript.routes.api.projects.ListProjectRoute;
import com.ddlscript.routes.api.projects.PostProjectRoute;
import com.ddlscript.routes.api.projects.scripts.ListScriptRoute;
import spark.RouteGroup;
import spark.Spark;

public class ProjectsApiRouteGroup implements RouteGroup {
	@Override
	public void addRoutes() {
		Spark.get("", new ListProjectRoute());
		Spark.post("", new PostProjectRoute());

		Spark.path("/:project", () -> {
			Spark.get("", new GetProjectRoute());

			Spark.path("/scripts", () -> {
				Spark.get("", new ListScriptRoute());

			});
		});
	}
}
