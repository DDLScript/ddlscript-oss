package com.ddlscript.repository.embedded;

import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Consumer;

public class EmbeddedDataSourceBuilder {

	@Setter
	@NonNull
	private String directory = "./data/";

	@Setter
	@NonNull
	private String fileName = "ddlscript";

	private static void setStringBasedProperty(
			@NonNull final Properties withProperties
			, @NonNull final String withKey
			, @NonNull final Consumer<String> withConsumer
	) {
		if (withProperties.containsKey(withKey)) {
			withConsumer.accept(withProperties.getProperty(withKey));
		}
	}

	public static EmbeddedDataSourceBuilder builder() {
		return new EmbeddedDataSourceBuilder();
	}

	public static EmbeddedDataSourceBuilder fromProperties(@NonNull final Properties withProperties) {
		var instance = EmbeddedDataSourceBuilder.builder();
		EmbeddedDataSourceBuilder.setStringBasedProperty(withProperties, "File.Name", instance::setFileName);
		EmbeddedDataSourceBuilder.setStringBasedProperty(withProperties, "Directory", instance::setDirectory);
		return instance;
	}

	public DataSource build() {
		Path currentRelativePath = Paths.get(this.directory + this.fileName)
				.toAbsolutePath();
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl("jdbc:h2:file:" + currentRelativePath);
		basicDataSource.setTestOnCreate(true);
		basicDataSource.setTestOnBorrow(true);
		return basicDataSource;
	}
}
