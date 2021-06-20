package com.ddlscript.routegroups.api;

import com.ddlscript.routes.api.sessions.DeleteSessionRoute;
import com.ddlscript.routes.api.sessions.GetSessionRoute;
import com.ddlscript.routes.api.sessions.PostSessionRoute;
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
