package com.ddlscript;

import lombok.experimental.UtilityClass;

import java.util.logging.Logger;

@UtilityClass
public class Application {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	/**
	 * Main entry point for the application.
	 *
	 * @param args
	 * 		Command Line Arguments
	 */
	public void main(String... args) {
		LOGGER.info("Starting DDL Script Webserver");
	}
}
