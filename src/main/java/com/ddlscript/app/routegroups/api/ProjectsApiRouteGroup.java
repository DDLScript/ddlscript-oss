package com.ddlscript.app.routegroups.api;

import com.ddlscript.app.routes.api.projects.DeleteProjectRoute;
import com.ddlscript.app.routes.api.projects.GetProjectRoute;
import com.ddlscript.app.routes.api.projects.ListProjectRoute;
import com.ddlscript.app.routes.api.projects.PatchProjectRoute;
import com.ddlscript.app.routes.api.projects.PostProjectRoute;
import com.ddlscript.app.routes.api.projects.scripts.ListScriptRoute;
import com.ddlscript.app.routes.api.projects.scripts.PostScriptRoute;
import spark.RouteGroup;
import spark.Spark;

public class ProjectsApiRouteGroup implements RouteGroup {
	@Override
	public void addRoutes() {
		Spark.get("", new ListProjectRoute());
		Spark.post("", new PostProjectRoute());

		Spark.path("/:project", () -> {
			Spark.get("", new GetProjectRoute());
			Spark.patch("", new PatchProjectRoute());
			Spark.delete("", new DeleteProjectRoute());

			Spark.path("/scripts", () -> {
				Spark.get("", new ListScriptRoute());
				Spark.post("", new PostScriptRoute());

			});
		});
	}
}
