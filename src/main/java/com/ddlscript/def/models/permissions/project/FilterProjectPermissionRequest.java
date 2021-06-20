package com.ddlscript.def.models.permissions.project;

import com.ddlscript.def.models.projects.ProjectModel;
import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.sdk.models.FilterableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request to retrieve a list of system permissions.
 */
public class FilterProjectPermissionRequest implements FilterableRequest<ProjectPermission> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static FilterProjectPermissionRequestBuilder builder() {
		return new FilterProjectPermissionRequestBuilder();
	}

	/**
	 * Project to retrieve permissions for.
	 */
	@Getter
	@Setter
	@NonNull
	private ProjectModel project;

	/**
	 * User whom the projects must be accessible to.
	 */
	@Getter
	@Setter
	@NonNull
	private UserModel accessibleToUser;

	/**
	 * Default Constructor.
	 */
	private FilterProjectPermissionRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private FilterProjectPermissionRequest(@NonNull FilterProjectPermissionRequest other) {
		this.setProject(other.getProject());
		this.setAccessibleToUser(other.getAccessibleToUser());
	}

	public static class FilterProjectPermissionRequestBuilder
			implements
			FilterableRequestBuilder<ProjectPermission, FilterProjectPermissionRequest, FilterProjectPermissionRequestBuilder> {

		private final FilterProjectPermissionRequest request = new FilterProjectPermissionRequest();

		public FilterProjectPermissionRequestBuilder setProject(@NonNull final ProjectModel withProjectModel) {
			this.request.setProject(withProjectModel);
			return this;
		}

		public FilterProjectPermissionRequestBuilder setAccessibleToUser(@NonNull final UserModel withUserModel) {
			this.request.setAccessibleToUser(withUserModel);
			return this;
		}

		@Override
		public FilterProjectPermissionRequest build() {
			return new FilterProjectPermissionRequest(this.request);
		}
	}
}
