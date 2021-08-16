package com.ddlscript.def.projects;

import com.ddlscript.def.users.UserModel;
import com.ddlscript.sdk.models.UpdatableRequest;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A request for creating a new User Group instance.
 */
public class UpdateProjectRequest implements UpdatableRequest<ProjectModel> {

	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Display name to assign to the project.
	 */
	@Getter
	private String name;

	/**
	 * User who is updating the project.
	 */
	@Getter
	@Setter
	@NonNull
	private UserModel userUpdated;

	/**
	 * SQL Template to appear at the start of each script.
	 */
	@Getter
	@Setter
	private String templateBeforeAll;

	/**
	 * SQL Template to appear before each statement on a script.
	 */
	@Getter
	@Setter
	private String templateBeforeEach;

	/**
	 * SQL Template to appear after each statement on a string.
	 */
	@Getter
	@Setter
	private String templateAfterEach;

	/**
	 * SQL Template to appear at the end of each script.
	 */
	@Getter
	@Setter
	private String templateAfterAll;

	/**
	 * Default Constructor.
	 */
	private UpdateProjectRequest() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private UpdateProjectRequest(@NonNull UpdateProjectRequest other) {
		this.setName(other.getName());
		this.setUserUpdated(other.getUserUpdated());
		this.setTemplateAfterAll(other.getTemplateAfterAll());
		this.setTemplateAfterEach(other.getTemplateAfterEach());
		this.setTemplateBeforeAll(other.getTemplateBeforeAll());
		this.setTemplateBeforeEach(other.getTemplateBeforeEach());
	}

	private void setName(final String withName) {
		if (withName == null) {
			this.name = null;
			return;
		}

		this.name = StringParser.of(withName)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(ProjectModel.MAX_NAME_LENGTH)
				.orElseThrow();
	}

	public static class Builder
			implements
			UpdatableRequestBuilder<ProjectModel, UpdateProjectRequest, Builder> {

		private final UpdateProjectRequest request = new UpdateProjectRequest();

		public Builder setName(final String withName) {
			this.request.setName(withName);
			return this;
		}

		public Builder setUserUpdated(@NonNull final UserModel withModel) {
			this.request.setUserUpdated(withModel);
			return this;
		}

		/**
		 * Defines the SQL Template to appear after each statement on a string.
		 *
		 * @param withTemplate SQL Template.
		 * @return this builder instance to allow for method chaining.
		 */
		public Builder setTemplateAfterEach(final String withTemplate) {
			this.request.setTemplateAfterEach(withTemplate);
			return this;
		}

		/**
		 * Defines the SQL Template to appear at the end of each script.
		 *
		 * @param withTemplate SQL Template.
		 * @return this builder instance to allow for method chaining.
		 */
		public Builder setTemplateAfterAll(final String withTemplate) {
			this.request.setTemplateAfterAll(withTemplate);
			return this;
		}

		/**
		 * Defines the SQL Template to appear before each statement on a script.
		 *
		 * @param withTemplate SQL Template.
		 * @return this builder instance to allow for method chaining.
		 */
		public Builder setTemplateBeforeEach(final String withTemplate) {
			this.request.setTemplateBeforeEach(withTemplate);
			return this;
		}

		/**
		 * Defines the SQL Template to appear at the start of each script.
		 *
		 * @param withTemplate SQL Template.
		 * @return this builder instance to allow for method chaining.
		 */
		public Builder setTemplateBeforeAll(final String withTemplate) {
			this.request.setTemplateBeforeAll(withTemplate);
			return this;
		}

		@Override
		public UpdateProjectRequest build() {
			return new UpdateProjectRequest(this.request);
		}
	}
}
