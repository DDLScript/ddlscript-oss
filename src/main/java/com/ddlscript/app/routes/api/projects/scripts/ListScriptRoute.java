package com.ddlscript.app.routes.api.projects.scripts;

import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.scripts.FilterScriptRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.app.routes.AbstractAuthenticatedRoute;
import com.ddlscript.app.routes.AuthenticationContext;
import com.ddlscript.app.schema.CollectionSchema;
import com.ddlscript.app.schema.projects.scripts.ScriptSummarizedSchema;
import com.ddlscript.sdk.PaginatedCollection;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

public class ListScriptRoute extends AbstractAuthenticatedRoute<Void, CollectionSchema<ScriptSummarizedSchema>> {
	@Override
	public CollectionSchema<ScriptSummarizedSchema> handle(
			final AuthenticationContext withAuthenticationContext, final Void withBody, final Request request, final Response response
	) throws Exception {

		final var describeProjectRequest = DescribeProjectRequest.builder()
				.setIdentifier(new ProjectIdentifier(Integer.parseInt(request.params("project"))))
				.setAccessibleToUser(withAuthenticationContext.getUserModel())
				.build();

		final var projectModel = ControllerFactory.INSTANCE
				.getProjectController()
				.describe(describeProjectRequest)
				.orElseThrow();

		final var filter = FilterScriptRequest.builder()
				.setProject(projectModel)
				.build();

		final var collection = ControllerFactory.INSTANCE
				.getScriptController()
				.filter(filter);

		final var elements = collection.getElements()
				.stream()
				.map(scriptModel -> new ScriptSummarizedSchema(withAuthenticationContext, scriptModel))
				.collect(Collectors.toList());

		return new CollectionSchema<>(PaginatedCollection.<ScriptSummarizedSchema>builder()
				.setElements(elements)
				.setPageCount(collection.getPageCount())
				.setPageSize(collection.getPageSize())
				.build());
	}
}
