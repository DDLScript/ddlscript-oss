package com.ddlscript.routes.api.projects;

import com.ddlscript.def.models.projects.FilterProjectRequest;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
import com.ddlscript.schema.CollectionSchema;
import com.ddlscript.schema.projects.ProjectSummarizedSchema;
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
