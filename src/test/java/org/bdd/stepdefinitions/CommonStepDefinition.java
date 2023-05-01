package org.bdd.stepdefinitions;

import org.bdd.basetest.BaseTest;
import org.bdd.utilities.ConfigProvider;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonStepDefinition extends BaseTest {

	@Given("user launch the application URL")
	public void launchTheURLandAccessTheSite() {
		startDriver();
		getPageObjectManager().getCommonPage().launchApplicationURL(ConfigProvider.getAsString("application.url"));
	}

	@Then("user should be taken to post login page")
	public void user_should_be_taken_to_post_login_page() {

	}

}