package com.ddlscript.repository.embedded;

import com.ddlscript.sdk.RepositoryException;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * SQL Server specific exception being thrown.
 */
public class EmbeddedDatabaseException extends RepositoryException {

	/**
	 * SQL Query being executed.
	 */
	@Getter
	private String sqlQuery = null;

	/**
	 * Row Parser Class being returned.
	 */
	@Getter
	private Class<?> rowParserClass = null;

	/**
	 * Collection of parameters being passed into the query.
	 */
	@Getter
	private final Collection<Object> parameters = new ArrayList<>();

	/**
	 * Instantiates a new SQL Server Exception instance.
	 *
	 * @param sqlException
	 * 		SQL Exception being thrown.
	 * @param withSqlQuery
	 * 		SQL Query being executed.
	 * @param withRowParserClass
	 * 		DTO Class being returned.
	 * @param withParameters
	 * 		Array of parameters being passed into the query.
	 */
	public EmbeddedDatabaseException(
			final SQLException sqlException
			, final String withSqlQuery
			, final Class<?> withRowParserClass
			, final Object... withParameters
	) {
		super(sqlException);
		this.sqlQuery = withSqlQuery;
		this.rowParserClass = withRowParserClass;
		this.parameters.addAll(Arrays.asList(withParameters));
	}
}
