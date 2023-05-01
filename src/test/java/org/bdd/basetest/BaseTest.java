package org.bdd.basetest;

import org.bdd.initiate.InitiateBrowser;
import org.bdd.pageobjectmanager.PageObjectManager;
import org.bdd.utilities.BrowserAction;
import org.bdd.utilities.ConfigProvider;
import org.bdd.utilities.Log;
import org.openqa.selenium.WebDriver;

public class BaseTest {

	private static final ThreadLocal<PageObjectManager> pageObjectManager = new ThreadLocal<>();
	private static final ThreadLocal<BrowserAction> browserAction = new ThreadLocal<>();

	public void startDriver() {
		try {
			InitiateBrowser.startBrowser(ConfigProvider.getAsString("browser.name"));
			pageObjectManager.set(new PageObjectManager(getDriver()));
			browserAction.set(new BrowserAction(getDriver()));
		} catch (Exception e) {
			Log.error(e);
		}
	}

	public static WebDriver getDriver() {
		return InitiateBrowser.getDriver();
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager.get();
	}
	
	public BrowserAction getBrowserAction() {
		return browserAction.get();
	}

	public static void closeBrowser() {
		InitiateBrowser.closeBrowser();
	}


}
