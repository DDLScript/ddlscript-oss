package com.ddlscript.app.factories;

import com.ddlscript.repository.embedded.EmbeddedDataSourceBuilder;
import com.ddlscript.repository.embedded.EmbeddedServerBuilder;
import com.ddlscript.utils.PropertiesUtils;
import lombok.Getter;
import org.h2.tools.Server;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public enum DataSourceFactory {

	INSTANCE;

	/**
	 * Logger instance.
	 */
	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	@Getter
	private Server databaseServer;

	@Getter
	private DataSource dataSource;

	DataSourceFactory() {
		startDatabaseServer();
		createDataSource();

		Runtime.getRuntime()
				.addShutdownHook(new DatabaseShutdownThread());
	}

	public Optional<DataSource> optDataSource() {
		return Optional.ofNullable(this.getDataSource());
	}

	private void startDatabaseServer() {
		LOGGER.info("Starting Embedded Database Server");
		try {
			this.databaseServer = EmbeddedServerBuilder.builder()
					.build()
					.start();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void stopDatabaseServer() {
		LOGGER.info("Stopping Embedded Database Server");

		if (this.databaseServer == null) {
			return;
		}

		this.databaseServer.stop();
		this.databaseServer = null;
	}

	private void createDataSource() {
		LOGGER.info("Creating DataSource Connection");
		try {
			Properties properties = PropertiesUtils.fromResources("./datasources/embedded.properties");
			this.dataSource = EmbeddedDataSourceBuilder.fromProperties(properties)
					.build();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static class DatabaseShutdownThread extends Thread {
		@Override
		public void run() {
			super.run();
			DataSourceFactory.INSTANCE.stopDatabaseServer();
		}
	}
}
