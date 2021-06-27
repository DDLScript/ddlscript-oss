package com.ddlscript.app.routes.api.projects;

import com.ddlscript.def.projects.FilterProjectRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.CollectionSchema;
import com.ddlscript.app.schema.projects.ProjectSummarizedSchema;
import com.ddlscript.sdk.PaginatedCollection;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class ListProjectRoute extends AbstractAuthenticatedRoute<Void, CollectionSchema<ProjectSummarizedSchema>> {
	@Override
	public CollectionSchema<ProjectSummarizedSchema> handle(
			final AuthenticationContext withAuthenticationContext, final Void withBody, final Request request, final Response response
	) throws Exception {
		var filter = FilterProjectRequest.builder()
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var collection = ControllerFactory.INSTANCE
				.getProjectController()
		.filter(filter);

		var c = new ArrayList<ProjectSummarizedSchema>();
		for(var x : collection) {
			c.add(new ProjectSummarizedSchema(x));
		}

		return new CollectionSchema<>(PaginatedCollection.<ProjectSummarizedSchema>builder()
				.setElements(c)
				.setPageCount(collection.getPageCount())
				.setPageSize(collection.getPageSize())
				.build());
	}
}
