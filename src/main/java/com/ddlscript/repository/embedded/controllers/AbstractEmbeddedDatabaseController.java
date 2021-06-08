package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.repository.embedded.EmbeddedDatabaseException;
import lombok.NonNull;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Database controller.
 */
public abstract class AbstractEmbeddedDatabaseController {

	/**
	 * Static row processor.
	 */
	protected static final RowProcessor ROW_PROCESSOR = new BasicRowProcessor(new GenerousBeanProcessor());

	/**
	 * Query runner to be used with executing SQL statements.
	 */
	private final QueryRunner queryRunner;

	/**
	 * Default constructor.
	 */
	public AbstractEmbeddedDatabaseController(@NonNull final DataSource withDataSource) {
		this.queryRunner = new QueryRunner(withDataSource);
	}

	/**
	 * Returns a collection of instances instance.
	 *
	 * @param modelClass
	 * 		DTO Class being returned.
	 * @param sqlQuery
	 * 		SQL Query being executed.
	 * @param parameters
	 * 		Parameters being passed into the query.
	 * @param <T>
	 * 		Type of DTO class being returned.
	 * @return Collection of DTOs.
	 */
	protected <T> T describe(
			final Class<T> modelClass
			, final String sqlQuery
			, final Object... parameters
	) {
		try {
			return this.queryRunner.query(sqlQuery, new BeanHandler<>(modelClass, ROW_PROCESSOR), parameters);
		} catch (SQLException sqlException) {
			throw new EmbeddedDatabaseException(
					sqlException
					, sqlQuery
					, modelClass
					, parameters
			);
		}
	}

	/**
	 * Inserts and returns a new instance.
	 *
	 * @param modelClass
	 * 		DTO Class being returned.
	 * @param sqlQuery
	 * 		SQL Query being executed.
	 * @param parameters
	 * 		Parameters being passed into the query.
	 * @param <T>
	 * 		Type of DTO class being returned.
	 * @return DTO
	 */
	protected <T> T insert(
			final Class<T> modelClass
			, final String sqlQuery
			, final Object... parameters
	) {
		try {
			return this.queryRunner.insert(sqlQuery, new BeanHandler<>(modelClass, ROW_PROCESSOR), parameters);
		} catch (SQLException sqlException) {
			throw new EmbeddedDatabaseException(
					sqlException
					, sqlQuery
					, modelClass
					, parameters
			);
		}
	}

	/**
	 * Updates and returns the number of rows that were updated..
	 *
	 * @param sqlQuery
	 * 		SQL Query being executed.
	 * @param parameters
	 * 		Parameters being passed into the query.
	 * @return number of updated rows
	 */
	protected int update(
			final String sqlQuery
			, final Object... parameters
	) {
		try {
			return this.queryRunner.update(sqlQuery, parameters);
		} catch (SQLException sqlException) {
			throw new EmbeddedDatabaseException(
					sqlException
					, sqlQuery
					, null
					, parameters
			);
		}
	}
}
