package com.ddlscript.repository.embedded.projects;

import com.ddlscript.def.projects.ProjectController;
import com.ddlscript.def.projects.CreateProjectRequest;
import com.ddlscript.def.projects.DescribeProjectRequest;
import com.ddlscript.def.projects.FilterProjectRequest;
import com.ddlscript.def.projects.ProjectIdentifier;
import com.ddlscript.def.projects.ProjectModel;
import com.ddlscript.def.projects.UpdateProjectRequest;
import com.ddlscript.repository.embedded.AbstractEmbeddedDatabaseController;
import com.ddlscript.sdk.PaginatedCollection;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmbeddedDatabaseProjectController
		extends AbstractEmbeddedDatabaseController
		implements ProjectController {

	private static final String SQL_FILTER = ResourceUtils.getResourceAsString("/database/dml/projects/filter.sql");

	private static final String SQL_FIND = ResourceUtils.getResourceAsString("/database/dml/projects/find.sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString("/database/dml/projects/create.sql");

	private static final String SQL_DELETE = ResourceUtils.getResourceAsString("/database/dml/projects/delete.sql");

	private static final String SQL_UPDATE = ResourceUtils.getResourceAsString("/database/dml/projects/update.sql");

	private static final String SQL_DESCRIBE = ResourceUtils.getResourceAsString("/database/dml/projects/describe.sql");

	private static final String SQL_ADD_USER = ResourceUtils.getResourceAsString("/database/dml/projects/users/create.sql");

	private static final String SQL_ADD_USER_PERMISSIONS = ResourceUtils.getResourceAsString("/database/dml/projects/users/permissions/create.sql");

	public EmbeddedDatabaseProjectController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> find(
			final ProjectIdentifier withIdentifier
	) {
		var instance = super.describe(
				ProjectRowData.class
				, SQL_FIND
				, withIdentifier.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseProjectModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> create(final CreateProjectRequest withRequest) {
		var rowData = super.insert(
				ProjectRowData.class
				, SQL_CREATE
				, withRequest.getName()
				, null
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);
		var projectIdentifier = new ProjectIdentifier(rowData.getId());

		super.update(
				SQL_ADD_USER
				, projectIdentifier.getRawValue()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);

		super.update(
				SQL_ADD_USER_PERMISSIONS
				, projectIdentifier.getRawValue()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);

		return this.find(projectIdentifier);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public PaginatedCollection<ProjectModel> filter(@NonNull final FilterProjectRequest withRequest) {
		List<ProjectModel> rowData = super.filter(
				ProjectRowData.class
				, SQL_FILTER
				, withRequest.getAccessibleToUser()
						.getIdentifier()
						.getRawValue()
		)
				.stream()
				.map(EmbeddedDatabaseProjectModel::new)
				.collect(Collectors.toList());

		return PaginatedCollection.<ProjectModel>builder()
				.setElements(rowData)
				.setPageSize(Integer.MAX_VALUE)
				.setPageCount(1)
				.build();
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> describe(@NonNull final DescribeProjectRequest withRequest) {
		var instance = super.describe(
				ProjectRowData.class
				, SQL_DESCRIBE
				, withRequest.getIdentifier().getRawValue()
				, withRequest.getAccessibleToUser()
						.getIdentifier()
						.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseProjectModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<ProjectModel> update(@NonNull final ProjectModel withProject, @NonNull final UpdateProjectRequest withRequest) {
		super.update(
				SQL_UPDATE
				, withRequest.getName()
				, withRequest.getTemplateBeforeAll()
				, withRequest.getTemplateBeforeEach()
				, withRequest.getTemplateAfterEach()
				, withRequest.getTemplateAfterAll()
				, withProject.getIdentifier()
						.getRawValue()
		);

		return this.find(withProject.getIdentifier());
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void delete(@NonNull final ProjectModel withProject) {
		super.update(
				SQL_DELETE
				, withProject.getIdentifier()
						.getRawValue()
		);
	}
}
