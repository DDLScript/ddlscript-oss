package com.ddlscript.repository.embedded;

import com.ddlscript.utils.ResourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class EmbeddedDatabaseMutator {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(EmbeddedDatabaseMutator.class.getName());

	private static final String DDL_RESOURCE_FOLDER = "database/ddl/";

	private EmbeddedDatabaseMutator() { }

	public static void update(final DataSource withDataSource) {
		final QueryRunner queryRunner = new QueryRunner(withDataSource);
		final int lastMutationId = EmbeddedDatabaseMutator.getLastAppliedMutationId(queryRunner);
		LOGGER.info("Last Database Mutation ID: " + lastMutationId);

		EmbeddedDatabaseMutator.getResourceFolderFiles()
				.stream()
				.filter(EmbeddedDatabaseMutator.getFilePredicate(lastMutationId))
				.sorted(EmbeddedDatabaseMutator.getFileComparator())
				.forEach(EmbeddedDatabaseMutator.getMutationApplicator(queryRunner));
	}

	private static List<String> showTables(final QueryRunner withQueryRunner) {
		try {
			return withQueryRunner.query("SHOW TABLES;", new ColumnListHandler<>(1));
		} catch (SQLException sqlException) {
			throw new RuntimeException("Unable to retrieve list of tables from the database.", sqlException);
		}
	}

	private static int getLastAppliedMutationId(final QueryRunner withQueryRunner) {
		if (!EmbeddedDatabaseMutator.showTables(withQueryRunner)
				.contains("DDLMigration".toUpperCase(Locale.ROOT))) {
			return -1;
		}

		try {
			return withQueryRunner.query(
					"SELECT MAX(DDLMigration.VERSION) FROM DDLMigration;"
					, new ScalarHandler<>()
			);
		} catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	private static List<File> getResourceFolderFiles() {
		return Optional.of(Thread.currentThread())
				.map(Thread::getContextClassLoader)
				.map(classLoader -> classLoader.getResource(DDL_RESOURCE_FOLDER))
				.map(URL::getPath)
				.map(File::new)
				.map(File::listFiles)
				.map(Arrays::asList)
				.orElseThrow();
	}

	private static Consumer<File> getMutationApplicator(final QueryRunner withQueryRunner) {
		return withFile -> {
			final int mutationId = Integer.parseInt(withFile.getName()
					.split("\\.")[0]);
			LOGGER.info("Applying Mutation ID: " + mutationId);

			final String sql = ResourceUtils.getResourceAsString("/" + DDL_RESOURCE_FOLDER + withFile.getName());

			try {
				withQueryRunner.execute(sql);

				final String insertSql = "INSERT INTO `DDLMigration` (VERSION, DDL, TIMESTAMP_APPLIED)"
						+ " VALUES (?, ?, CURRENT_TIMESTAMP());";
				withQueryRunner.execute(insertSql, mutationId, sql);
			} catch (SQLException sqlException) {
				throw new RuntimeException(sqlException);
			}
		};
	}

	private static Predicate<File> getFilePredicate(final int withLastAppliedMutationId) {
		return file -> {
			String s = file.getName()
					.split("\\.")[0];
			return Integer.parseInt(s) > withLastAppliedMutationId;
		};
	}

	private static Comparator<File> getFileComparator() {
		return (withFirstFile, withSecondFile) -> {
			int intA = Optional.of(withFirstFile)
					.map(File::getName)
					.map(m -> m.split("\\.")[0])
					.map(Integer::parseInt)
					.orElseThrow();

			int intB = Optional.of(withSecondFile)
					.map(File::getName)
					.map(m -> m.split("\\.")[0])
					.map(Integer::parseInt)
					.orElseThrow();

			return Integer.compare(intA, intB);
		};
	}
}
