package org.bdd.pageobjectmanager;

import org.bdd.pages.CommonPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	private WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	private CommonPage commonPage;


	public CommonPage getCommonPage() {
		return (commonPage == null) ? commonPage = new CommonPage(driver) : commonPage;
	}

}
