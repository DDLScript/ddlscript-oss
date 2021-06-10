package com.ddlscript.def.models.projects;

import com.ddlscript.def.models.users.UserModel;
import com.ddlscript.sdk.models.FilterableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request to retrieve a list of rojects.
 */
public class FilterProjectRequest implements FilterableRequest<ProjectModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static CreateProjectRequestBuilder builder() {
		return new CreateProjectRequestBuilder();
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
	private FilterProjectRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private FilterProjectRequest(@NonNull FilterProjectRequest other) {
		this.setAccessibleToUser(other.getAccessibleToUser());
	}

	public static class CreateProjectRequestBuilder
			implements
			FilterableRequestBuilder<ProjectModel, FilterProjectRequest, CreateProjectRequestBuilder> {

		private final FilterProjectRequest request = new FilterProjectRequest();

		public CreateProjectRequestBuilder setAccessibleToUser(@NonNull final UserModel withUserModel) {
			this.request.setAccessibleToUser(withUserModel);
			return this;
		}

		@Override
		public FilterProjectRequest build() {
			return new FilterProjectRequest(this.request);
		}
	}
}
