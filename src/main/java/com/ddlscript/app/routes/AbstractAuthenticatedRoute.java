package com.ddlscript.app.routes;

import com.ddlscript.def.permissions.system.FilterSystemPermissionRequest;
import com.ddlscript.app.factories.ControllerFactory;
import com.ddlscript.sdk.AbstractRoute;
import lombok.NonNull;
import spark.Request;
import spark.Response;

public abstract class AbstractAuthenticatedRoute<INPUT, OUTPUT> extends AbstractRoute<INPUT, OUTPUT> {

	public abstract OUTPUT handle(
			final AuthenticationContext withAuthenticationContext, final INPUT withBody, final Request request, final Response response
	) throws Exception;

	public AbstractAuthenticatedRoute() {
		super();
	}

	public AbstractAuthenticatedRoute(@NonNull Class<INPUT> withInputClass) {
		super(withInputClass);
	}

	@Override
	public OUTPUT handle(final INPUT withBody, final Request request, final Response response) throws Exception {
		final var sessionId = request.cookie("sesid");
		if (sessionId == null || sessionId.isBlank()) {
			return null;
		}

		var sessionModel = ControllerFactory.INSTANCE.getSessionController()
				.describe(builder -> builder.setToken(sessionId))
				.orElseThrow();

		// search for a user
		var userModel = ControllerFactory.INSTANCE
				.getUserController()
				.find(sessionModel.getCreatedUserIdentifier())
				.filter(user -> !user.isArchived())
				.orElseThrow();

		// fetch the system permissions
		var systemPermissionsFilter = FilterSystemPermissionRequest.builder()
				.setAccessibleToUser(userModel)
				.build();
		var systemPermissions = ControllerFactory.INSTANCE
				.getSystemPermissionController()
				.filter(systemPermissionsFilter);

		var authenticationContext = AuthenticationContext.builder()
				.setSessionModel(sessionModel)
				.setUserModel(userModel)
				.setSystemPermissions(systemPermissions.getElements())
				.build();

		return this.handle(authenticationContext, withBody, request, response);
	}
}
