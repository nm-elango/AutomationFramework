package org.bdd.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	//Initialize Log4j logs
	private static Logger Log = LogManager.getLogger();

	public static void startTestCase(String testCaseName) {
		Log.info(
				"####################################################################################################");
		Log.info("***********************            " + testCaseName + "              *************************");
	}

	public static void endTestCase(String testCaseName) {
		Log.info("************************           " + "E---N---D" + "      ***************************");
		Log.info(
				"####################################################################################################");
	}
	
	public static void error(String message) {
		Log.error(message);
	}
	
	public static void error(Exception e) {
		Log.error(e);
	}
	
	public static void warn(String message) {
		Log.warn(message);
	}
	
	public static void info(String message) {
		Log.info(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}
}
