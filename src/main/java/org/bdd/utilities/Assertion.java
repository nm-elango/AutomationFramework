package org.bdd.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Assertion {

	private static Logger Log = LogManager.getLogger();

	public static void assertEquals(String expected, String actual, String message) {
		Log.info("asserEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertEquals(expected, actual, message);
	}

	public static void assertEquals(int expected, int actual, String message) {
		Log.info("asserEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertEquals(expected, actual, message);
	}

	public static void assertEquals(boolean expected, boolean actual, String message) {
		Log.info("asserEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertEquals(expected, actual, message);
	}

	public static void assertEquals(double expected, double actual, String message) {
		Log.info("asserEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertEquals(expected, actual, message);
	}

	public static void assertTrue(boolean actual, String message) {
		Log.info("asserTrue : actual = " + actual + " : Message = " + message);
		Assert.assertTrue(actual, message);
	}

	public static void assertFalse(boolean actual, String message) {
		Log.info("asserFalse : actual = " + actual + " : Message = " + message);
		Assert.assertFalse(actual, message);
	}

	public static void assertNull(Object object, String message) {
		Log.info("asserNull : actual = " + object + " : Message = " + message);
		Assert.assertNull(object, message);
	}

	public static void assertNotNull(Object object, String message) {
		Log.info("asserNotNull : actual = " + object + " : Message = " + message);
		Assert.assertNotNull(object, message);
	}

	public static void assertNotEquals(String expected, String actual, String message) {
		Log.info("asserNotEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertNotEquals(expected, actual, message);
	}

	public static void assertNotEquals(int expected, int actual, String message) {
		Log.info("asserNotEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertNotEquals(expected, actual, message);
	}

	public static void assertNotEquals(boolean expected, boolean actual, String message) {
		Log.info("asserNotEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertNotEquals(expected, actual, message);
	}

	public static void assertNotEquals(double expected, double actual, String message) {
		Log.info("asserNotEquals: expected = (" + expected + ") actual = (" + actual + ")");
		Assert.assertNotEquals(expected, actual, message);
	}
}
