package org.bdd.listener;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bdd.extentreports.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static Logger Log = LogManager.getLogger();
	private Map<String, String> suiteParams = new HashMap<>();

	private static String getScenarioName(ITestResult iTestResult) {
		return iTestResult.getParameters()[0].toString().replaceAll("\"", "");
	}

	private static String getFeatureFileDescription(ITestResult iTestResult) {
		return iTestResult.getParameters()[1].toString().replaceAll("\"", "");
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		suiteParams = iTestContext.getSuite().getXmlSuite().getParameters();
	}

	public Map<String, String> getSuiteParameters() {
		return suiteParams;
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentReportManager.getInstance().flush();
		ExtentReportManager.endTest();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log.info(getScenarioName(iTestResult) + " - " + getFeatureFileDescription(iTestResult));
		ExtentReportManager.startTest(getScenarioName(iTestResult), getFeatureFileDescription(iTestResult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info(getScenarioName(iTestResult) + " passed successfully.");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.warn(getScenarioName(iTestResult) + " failed");
		if (ExtentReportManager.getTest() != null) {
			ExtentReportManager.getTest().log(Status.FAIL, "Test Step Failed: " + iTestResult.getThrowable());
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		if (ExtentReportManager.getTest() != null) {
			ExtentReportManager.getTest().log(Status.SKIP, iTestResult.getName() + " execution skipped.");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult iTestResult) {
		Log.info("");
	}

}
