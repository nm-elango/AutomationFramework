package org.bdd.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bdd.extentreports.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Screenshot {

	private static Logger Log = LogManager.getLogger();
	private static String screenshotFolderPath;
	private final static String screenshotPath = "/AutomationResults/Screenshots/";

	static {
		createDirectory();
	}

	private static void createDirectory() {
		screenshotFolderPath = System.getProperty("user.dir") + screenshotPath;
		Log.info("Screenshot path = " + screenshotFolderPath);
		File file = new File(screenshotFolderPath);
		if (!file.exists() && !file.mkdir()) {
			Log.warn("Failed to create screenshot directory");
		}
	}

	protected static String captureScreenshot(WebDriver driver, String screenshotName) {
		String randomNumber = RandomStringUtils.randomNumeric(5);
		String destinationPath = screenshotFolderPath + screenshotName + randomNumber + ".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourceFile, new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.info("Screenshot - " + destinationPath);
		return destinationPath;
	}
	
	public static void passTest(String passMessage) {
		passTestWithScreenshot(null, passMessage);
	}

	public static void failTest(String failMessage) {
		failTestWithScreenshot(null, failMessage);
	}

	public static void passTestWithScreenshot(WebDriver driver, String passMessage) {
		ExtentTest extentTest = ExtentReportManager.getTest();
		if (extentTest != null) {
			if (driver != null) {
				String path = Screenshot.captureScreenshot(driver, "Screenshot");
				try {
					extentTest.pass(passMessage, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				extentTest.pass(passMessage);
			}
		}
	}

	public static void failTestWithScreenshot(WebDriver driver, String failMessage) {
		ExtentTest extentTest = ExtentReportManager.getTest();
		if (extentTest != null) {
			if (driver != null) {
				String path = Screenshot.captureScreenshot(driver, "Screenshot");
				try {
					extentTest.fail(failMessage, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					Assert.assertTrue(false, failMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				extentTest.fail(failMessage);
				Assert.assertTrue(false, failMessage);
			}
		}
	}
}
