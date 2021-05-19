package com.ddlscript;

import com.ddlscript.repository.embedded.EmbeddedDataSourceBuilder;
import com.ddlscript.repository.embedded.EmbeddedDatabaseMutator;
import com.ddlscript.repository.embedded.EmbeddedServerBuilder;
import com.ddlscript.utils.PropertiesUtils;
import lombok.experimental.UtilityClass;
import org.h2.tools.Server;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

@UtilityClass
public class Application {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	private Server databaseServer;

	private DataSource dataSource;

	/**
	 * Main entry point for the application.
	 *
	 * @param args
	 * 		Command Line Arguments
	 */
	public void main(String... args) {
		LOGGER.info("Starting DDL Script Webserver");

		Runtime.getRuntime()
				.addShutdownHook(new DatabaseShutdownThread());

		Application.databaseServer = startDatabaseServer();
		Application.dataSource = createDataSource();
		EmbeddedDatabaseMutator.update(Application.dataSource);
	}

	private Server startDatabaseServer() {
		LOGGER.info("Starting Embedded Database Server");
		try {
			return EmbeddedServerBuilder.builder()
					//.allowOthers()
					.build()
					.start();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private DataSource createDataSource() {
		LOGGER.info("Creating DataSource Connection");
		try {
			Properties properties = PropertiesUtils.fromResources("./datasources/embedded.properties");
			return EmbeddedDataSourceBuilder.fromProperties(properties)
					.build();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static class DatabaseShutdownThread extends Thread {
		@Override
		public void run() {
			super.run();
			LOGGER.info("Stopping Embedded Database Server");

			if (Application.databaseServer == null) {
				return;
			}

			Application.databaseServer.stop();
		}
	}
}
