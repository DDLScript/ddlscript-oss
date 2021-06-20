package com.ddlscript.routes.api.projects.scripts;

import com.ddlscript.def.models.projects.DescribeProjectRequest;
import com.ddlscript.def.models.projects.ProjectIdentifier;
import com.ddlscript.def.models.projects.scripts.FilterScriptRequest;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.routes.AuthenticationContext;
import com.ddlscript.schema.CollectionSchema;
import com.ddlscript.schema.projects.scripts.ScriptSummarizedSchema;
import com.ddlscript.sdk.PaginatedCollection;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class ListScriptRoute extends AbstractAuthenticatedRoute<Void, CollectionSchema<ScriptSummarizedSchema>> {
	@Override
	public CollectionSchema<ScriptSummarizedSchema> handle(
			final AuthenticationContext withAuthenticationContext, final Void withBody, final Request request, final Response response
	) throws Exception {

		// fetch the project instance
		var describeProjectRequest = DescribeProjectRequest.builder()
				.setIdentifier(new ProjectIdentifier(Integer.parseInt(request.params("project"))))
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();
		var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.describe(describeProjectRequest)
				.orElseThrow();

		var filter = FilterScriptRequest.builder()
				.setProject(projectModel)
				.build();
		var collection = ControllerFactory.INSTANCE
				.getScriptController()
				.filter(filter);

		var c = new ArrayList<ScriptSummarizedSchema>();
		for(var x : collection) {
			c.add(new ScriptSummarizedSchema(x));
		}

		return new CollectionSchema<>(PaginatedCollection.<ScriptSummarizedSchema>builder()
				.setElements(c)
				.setPageCount(collection.getPageCount())
				.setPageSize(collection.getPageSize())
				.build());
	}
}
