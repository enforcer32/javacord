package com.github.enforcer32.javacord.core;

import org.slf4j.LoggerFactory;

public class Logger {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("javacordLogger");

	public static void info(String format, Object... arguments) {
		LOGGER.info(format, arguments);
	}

	public static void warn(String format, Object... arguments) {
		LOGGER.warn(format, arguments);
	}

	public static void error(String format, Object... arguments) {
		LOGGER.error(format, arguments);
	}

	public static void critical(String format, Object... arguments) {
		LOGGER.error(format, arguments, new Throwable(format));
	}

	public static void trace(String format, Object... arguments) {
		LOGGER.trace(format, arguments);
	}

	public static void debug(String format, Object... arguments) {
		LOGGER.debug(format, arguments);
	}
}
