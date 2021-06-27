package com.ddlscript.app.routegroups.api;

import com.ddlscript.app.routes.api.sessions.DeleteSessionRoute;
import com.ddlscript.app.routes.api.sessions.GetSessionRoute;
import com.ddlscript.app.routes.api.sessions.PostSessionRoute;
import spark.RouteGroup;
import spark.Spark;

public class SessionApiRouteGroup implements RouteGroup {
	@Override
	public void addRoutes() {
		Spark.get("", new GetSessionRoute());
		Spark.post("", new PostSessionRoute());
		Spark.delete("", new DeleteSessionRoute());
	}
}
