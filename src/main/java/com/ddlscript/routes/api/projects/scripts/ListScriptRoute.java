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
