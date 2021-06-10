package com.ddlscript.routes.api.projects;

import com.ddlscript.def.models.projects.FilterProjectRequest;
import com.ddlscript.def.models.sessions.SessionModel;
import com.ddlscript.def.models.users.UserIdentifier;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.factories.ControllerFactory;
import com.ddlscript.routes.AbstractAuthenticatedRoute;
import com.ddlscript.schema.CollectionSchema;
import com.ddlscript.schema.projects.ProjectSummarizedSchema;
import com.ddlscript.sdk.PaginatedCollection;
import spark.Request;
import spark.Response;

import java.time.Instant;
import java.util.ArrayList;

public class ListProjectRoute extends AbstractAuthenticatedRoute<Void, CollectionSchema<ProjectSummarizedSchema>> {
	@Override
	public CollectionSchema<ProjectSummarizedSchema> handle(
			final SessionModel withSession, final Void withBody, final Request request, final Response response
	) throws Exception {
		var filter = FilterProjectRequest.builder()
				.setAccessibleToUser(new UserModel() {
					@Override
					public Instant getTimestampCreated() {
						return null;
					}

					@Override
					public Instant getTimestampArchived() {
						return null;
					}

					@Override
					public String getName() {
						return null;
					}

					@Override
					public String getUsername() {
						return null;
					}

					@Override
					public String getEmail() {
						return null;
					}

					@Override
					public boolean isAdministrator() {
						return false;
					}

					@Override
					public boolean verifyPassword(final String withPassword) {
						return false;
					}

					@Override
					public UserIdentifier getIdentifier() {
						return null;
					}
				})
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
