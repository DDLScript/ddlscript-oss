package com.ddlscript.def.projects;

import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.models.DescribableRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request to retrieve a list of rojects.
 */
public class DescribeProjectRequest implements DescribableRequest<ProjectModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static DescribeProjectRequestBuilder builder() {
		return new DescribeProjectRequestBuilder();
	}

	/**
	 * Identifier of the project to return.
	 */
	@Getter
	@Setter
	@NonNull
	private ProjectIdentifier identifier;

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
	private DescribeProjectRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private DescribeProjectRequest(@NonNull DescribeProjectRequest other) {
		this.setIdentifier(other.getIdentifier());
		this.setAccessibleToUser(other.getAccessibleToUser());
	}

	public static class DescribeProjectRequestBuilder
			implements
			DescribableRequestBuilder<ProjectModel, DescribeProjectRequest, DescribeProjectRequestBuilder> {

		private final DescribeProjectRequest request = new DescribeProjectRequest();

		public DescribeProjectRequestBuilder setIdentifier(@NonNull final ProjectIdentifier withIdentifier) {
			this.request.setIdentifier(withIdentifier);
			return this;
		}

		public DescribeProjectRequestBuilder setAccessibleToUser(@NonNull final UserModel withUserModel) {
			this.request.setAccessibleToUser(withUserModel);
			return this;
		}

		@Override
		public DescribeProjectRequest build() {
			return new DescribeProjectRequest(this.request);
		}
	}
}
