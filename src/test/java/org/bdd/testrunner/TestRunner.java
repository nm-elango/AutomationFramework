package org.bdd.testrunner;

import org.bdd.basetest.BaseTest;
import org.bdd.listener.TestNGCucumber;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;

@CucumberOptions(features = "src/test/resources/features/", glue = { "org.bdd.stepdefinitions" }, plugin = {
		"pretty" }, dryRun = false, tags = "@sample")

public class TestRunner extends TestNGCucumber {

	@AfterTest
	public void closeBrowser() {
		BaseTest.closeBrowser();
	}
}
