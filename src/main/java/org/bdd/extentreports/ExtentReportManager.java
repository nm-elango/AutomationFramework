package org.bdd.extentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bdd.utilities.Log;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static Set<Integer> extentThreadList = new HashSet<>();
	private static ExtentReports extent;
	private static final String TIME_STAMP = new SimpleDateFormat("dd.MMM.yyyy.HH.mm").format(new Date());
	private static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String EXTENT_REPORTS_FOLDER = WORKING_DIR + "/AutomationResults";
	private static final String REPORT_NAME = "ExtentReport_" + TIME_STAMP + ".html";
	private static final String EXTENT_REPORTS_PATH = EXTENT_REPORTS_FOLDER + File.separator + REPORT_NAME;

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(getCurrentThread());
	}

	private static synchronized int getCurrentThread() {
		return (int) (Thread.currentThread().getId());
	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			createReportsFolder();
			attachReporters();
		}
		return extent;
	}
	
	private static void createReportsFolder() {
		File file = new File(EXTENT_REPORTS_FOLDER);
		if (!file.exists() && !file.mkdir()) {
			Log.warn("Failed to create report directory");
		}
	}
	
	private static ExtentReports attachReporters() {
		extent = new ExtentReports();
		extent.attachReporter(initHtmlReporter());
		return extent;
	}
	
	private static ExtentSparkReporter initHtmlReporter() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(EXTENT_REPORTS_PATH);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setReportName("Execution-Results");
		htmlReporter.config().setTimeStampFormat("MMM dd, YYYY HH:mm:ss");
		return htmlReporter;
	}
	
	public static synchronized ExtentTest startTest(String scenarioName, final String featureDesc) {
		extent = getInstance();
		ExtentTest test = extent.createTest(scenarioName, featureDesc);
		extentTestMap.put(getCurrentThread(), test);
		extentThreadList.add(getCurrentThread());
		return test;
	}

	public static synchronized void endTest() {
		extentThreadList.remove(getCurrentThread());
		if (!extentTestMap.isEmpty() && extentThreadList.isEmpty()) {
			Set<Integer> set = new HashSet<Integer>();
			set = extentTestMap.keySet();
			for (Integer i : set) {
				extent.removeTest(extentTestMap.get(i));
			}
		}
	}
}
