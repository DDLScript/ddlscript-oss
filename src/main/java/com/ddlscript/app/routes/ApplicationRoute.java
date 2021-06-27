package com.ddlscript.app.routes;

import com.ddlscript.utils.ResourceUtils;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Main Application Route.
 */
public class ApplicationRoute implements Route {

	/**
	 * Html content of the webapp.
	 */
	private static final String HTML = ResourceUtils.getResourceAsString("/webroot/index.html");

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Object handle(final Request request, final Response response) throws Exception {
		response.type("text/html");
		return HTML;
	}
}
