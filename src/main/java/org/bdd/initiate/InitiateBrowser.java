package org.bdd.initiate;

import org.bdd.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitiateBrowser {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static void startBrowser(String browser) {
		Log.info("Browser to Initiate = " + browser);
		if (browser.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options  = new ChromeOptions();
		    options.addArguments("--disable-notifications");
			driver.set(new ChromeDriver(options));
		} else if (browser.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("EDGE")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else {
			Log.error("Invalid Browser - " + browser);
		}
		if (driver.get() != null)
			driver.get().manage().window().maximize();
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void closeBrowser() {
		if (driver.get() != null) {
			driver.get().close();
			driver.remove();
		}
	}
	

}
