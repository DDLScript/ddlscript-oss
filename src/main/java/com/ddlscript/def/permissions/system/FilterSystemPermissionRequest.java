package com.ddlscript.def.permissions.system;

import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.models.FilterableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request to retrieve a list of system permissions.
 */
public class FilterSystemPermissionRequest implements FilterableRequest<SystemPermission> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static FilterSystemPermissionRequestBuilder builder() {
		return new FilterSystemPermissionRequestBuilder();
	}

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
	private FilterSystemPermissionRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private FilterSystemPermissionRequest(@NonNull FilterSystemPermissionRequest other) {
		this.setAccessibleToUser(other.getAccessibleToUser());
	}

	public static class FilterSystemPermissionRequestBuilder
			implements
			FilterableRequestBuilder<SystemPermission, FilterSystemPermissionRequest, FilterSystemPermissionRequestBuilder> {

		private final FilterSystemPermissionRequest request = new FilterSystemPermissionRequest();

		public FilterSystemPermissionRequestBuilder setAccessibleToUser(@NonNull final UserModel withUserModel) {
			this.request.setAccessibleToUser(withUserModel);
			return this;
		}

		@Override
		public FilterSystemPermissionRequest build() {
			return new FilterSystemPermissionRequest(this.request);
		}
	}
}
