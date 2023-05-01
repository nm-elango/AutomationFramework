package org.bdd.pages;

import org.bdd.utilities.BrowserAction;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BrowserAction {

	private final WebDriver driver;

	public CommonPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void launchApplicationURL(String url) {
		navigateTo(url);
	}


}