package org.bdd.utilities;

import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserAction {

	private WebDriver driver;
	private FluentWait<WebDriver> fluentWait;
	private final JavascriptExecutor javascriptExecutor;
	private static final String SET_INPUT_COMMAND = "arguments[0].value='%s';";
	private static final String CLICK_COMMAND = "arguments[0].click();";
	private static final String JS_DISPLAY_COMMAND = "arguments[0].style.display='block';";
	private Duration pollingInterval = Duration.ofMillis(ConfigProvider.getAsInteger("POLLING_INTERVAL"));
	private Duration fluentWaitDuration = Duration.ofSeconds(ConfigProvider.getAsInteger("FLUENT_WAIT"));
	private static final int DEFAULT_IMPLICIT_WAIT = 0;

	private static Logger Log = LogManager.getLogger();

	public BrowserAction(WebDriver driver) {
		this.driver = driver;
		fluentWait = new FluentWait<>(driver).withTimeout(fluentWaitDuration).pollingEvery(pollingInterval)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class);
		javascriptExecutor = (JavascriptExecutor) driver;
	}

	/* Returning WebElement using by.class */
	protected WebElement getElement(final By byLocator) {
		return fluentWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(byLocator);
			}
		});
	}

	/* Returning WebElement using String */
	protected WebElement getElement(final String locator) {
		return getElement(By.xpath(locator));
	}

	/* Returning WebElements using by.class */
	protected List<WebElement> getElements(final By byLocator) {
		return fluentWait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(byLocator);
			}
		});
	}

	/* Returning WebElement using String */
	protected List<WebElement> getElements(final String locator) {
		return getElements(By.xpath(locator));
	}

	private void setImplicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}

	/* Launch Url */
	protected void get(String url) {
		Log.info("driver.get URL = " + url);
		if (url != null)
			driver.get(url);
	}

	protected void navigateTo(String url) {
		Log.info("driver.navigate().to URL = " + url);
		if (url != null)
			driver.navigate().to(url);
	}

	protected boolean isElementOnPage(final By byLocator) {
		setImplicitWait(DEFAULT_IMPLICIT_WAIT);
		boolean flag = !getElements(byLocator).isEmpty();
		Log.info("is Element (" + byLocator + ") On Page ? " + flag);
		setImplicitWait(ConfigProvider.getAsInteger("IMPLICIT_WAIT"));
		return flag;
	}

	protected boolean isElementOnPage(final String locator) {
		return isElementOnPage(By.xpath(locator));
	}

	protected boolean isEnabled(final By byLocator) {
		List<WebElement> elementList = getElements(byLocator);
		boolean enabled = false;
		if (!elementList.isEmpty()) {
			enabled = elementList.get(0).isEnabled();
		}
		Log.info("is Enabled (" + byLocator + ") ?" + enabled);
		return enabled;
	}

	protected boolean isEnabled(final String locator) {
		return isEnabled(By.xpath(locator));
	}

	protected boolean isDisplayed(final By byLocator) {
		List<WebElement> elementList = getElements(byLocator);
		boolean displayed = false;
		if (!elementList.isEmpty()) {
			displayed = elementList.get(0).isDisplayed();
		}
		Log.info("is Displayed (" + byLocator + ") ?" + displayed);
		return displayed;
	}

	protected boolean isDisplayed(final String locator) {
		return isDisplayed(By.xpath(locator));
	}

	protected boolean isSelected(final By byLocator) {
		List<WebElement> elementList = getElements(byLocator);
		boolean selected = false;
		if (!elementList.isEmpty()) {
			selected = elementList.get(0).isSelected();
		}
		Log.info("is Selected (" + byLocator + ") ?" + selected);
		return selected;
	}

	protected boolean isSelected(final String locator) {
		return isSelected(By.xpath(locator));
	}

	/* To get list of elements */
	protected int getElementsSize(final By byLocator) {
		int size = 0;
		if (isElementOnPage(byLocator)) {
			size = getElements(byLocator).size();
		}
		Log.info("get Elements size (" + byLocator + ") = " + size);
		return size;
	}

	protected int getElementsSize(final String locator) {
		return getElementsSize(By.xpath(locator));
	}

	protected void setInput(final By byLocator, final String value) {
		Log.info("set Input in (" + byLocator + ") with value = " + value);
		getElement(byLocator).sendKeys(value);
	}

	protected void setInput(final String locator, final String value) {
		Log.info("set Input in (" + locator + ") with value = " + value);
		getElement(locator).sendKeys(value);
	}

	protected void setInputJS(final By byLocator, final String value) {
		Log.info("set Input JS in (" + byLocator + ") with value = " + value);
		javascriptExecutor.executeScript(String.format(SET_INPUT_COMMAND, value), getElement(byLocator));
	}

	protected void setInputJS(final String locator, final String value) {
		setInputJS(By.xpath(locator), value);
	}

	protected void clearElement(final By byLocator) {
		Log.info("clear Element = " + byLocator);
		getElement(byLocator).clear();
	}

	protected void clearElement(final String locator) {
		Log.info("clear Element = " + locator);
		getElement(locator).clear();
	}

	protected String getText(final By byLocator) {
		Log.info("get Text from - " + byLocator);
		return getElement(byLocator).getText();
	}

	protected String getText(final String locator) {
		Log.info("get Text from - " + locator);
		return getElement(locator).getText();
	}

	protected String getAttribute(final By byLocator, final String attribute) {
		Log.info("get Attribute (" + attribute + ") from - " + byLocator);
		return getElement(byLocator).getAttribute(attribute);
	}

	protected String getAttribute(final String locator, final String attribute) {
		Log.info("get Attribute (" + attribute + ") from - " + locator);
		return getElement(locator).getAttribute(attribute);
	}

	protected void click(final By byLocator) {
		Log.info("click Element = " + byLocator);
		getElement(byLocator).click();
	}

	protected void click(final String locator) {
		Log.info("click Element = " + locator);
		getElement(locator).click();
	}

	protected void clickUsingJS(final By byLocator) {
		Log.info("click Element JS = " + byLocator);
		javascriptExecutor.executeScript(CLICK_COMMAND, getElement(byLocator));
	}

	protected void clickUsingJS(final String locator) {
		clickUsingJS(By.xpath(locator));
	}

	protected void makeElementVisibleAndClick(final By byLocator) {
		Log.info("make Element Visible and Click using JS = " + byLocator);
		WebElement element = getElement(byLocator);
		javascriptExecutor.executeScript(JS_DISPLAY_COMMAND, element);
		javascriptExecutor.executeScript(CLICK_COMMAND, element);
	}

	protected void makeElementVisibleAndClick(final String locator) {
		makeElementVisibleAndClick(By.xpath(locator));
	}

	protected void makeElementVisible(final By byLocator) {
		Log.info("make Element Visible using JS = " + byLocator);
		javascriptExecutor.executeScript(JS_DISPLAY_COMMAND, getElement(byLocator));
	}

	protected void makeElementVisible(final String locator) {
		Log.info("make Element Visible using JS = " + locator);
		javascriptExecutor.executeScript(JS_DISPLAY_COMMAND, getElement(locator));
	}

	protected void selectDropDownValue(final By dropDownArrow, final By valueLocators, final String value) {
		Log.info("select Dropdown value - " + value);
		if (isElementOnPage(dropDownArrow)) {
			click(dropDownArrow);
			List<WebElement> dropDownValues = getElements(valueLocators);
			for (WebElement ele : dropDownValues) {
				Log.info("Location values - " + ele.getText());
				if (ele.getText().trim().toUpperCase().equals(value.trim().toUpperCase())) {
					ele.click();
					break;
				}
			}
		}
	}

	protected void selectDropDownValue(final String dropDownArrow, final String valueLocators, final String value) {
		selectDropDownValue(By.xpath(dropDownArrow), By.xpath(valueLocators), value);
	}

	protected boolean waitForElement(final By byLocator, int waitTimeInSeconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds))
					.until(ExpectedConditions.elementToBeClickable(byLocator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public enum LOCATOR {
		LOCATOR1,
		LOCATOR2,
		LOCATOR3
	}

	protected LOCATOR waitForMultiElementVisible(By locator1, By locator2, int waitTimeInSeconds) {
		return waitForMultiElementVisible(locator1, locator2, null, waitTimeInSeconds);
	}
	
	protected LOCATOR waitForMultiElementVisible(By locator1, By locator2, By locator3, int waitTimeInSeconds) {
		int loopSeconds = 0, iterator = 0;
		LOCATOR locatorFound = null;
		
		if (locator1 != null)
			loopSeconds++;
		if (locator2 != null)
			loopSeconds++;
		if (locator3 != null)
			loopSeconds++;
		
		while (iterator < waitTimeInSeconds) {
			if (locator1 != null) {
				if(isElementOnPage(locator1)) {
					locatorFound = LOCATOR.LOCATOR1;
					break;
				}
				waitForSeconds(1);
			}
			if (locator2 != null) {
				if(isElementOnPage(locator2)) {
					locatorFound = LOCATOR.LOCATOR2;
					break;
				}
				waitForSeconds(1);
			}
			if (locator3 != null) {
				if(isElementOnPage(locator3)) {
					locatorFound = LOCATOR.LOCATOR3;
					break;
				}
				waitForSeconds(1);
			}
			iterator += loopSeconds;
		}
		return locatorFound;
	}

	protected boolean waitForElementNotVisible(final By byLocator, int waitTimeInSeconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds))
					.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected boolean waitForElement(final String locator, int waitTimeInSeconds) {
		return waitForElement(By.xpath(locator), waitTimeInSeconds);
	}

	protected void waitForSeconds(int waitTimeInSeconds) {
		try {
			Thread.sleep(waitTimeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getBase64EncodedValue(String decodedString) {
		return new String(Base64.getEncoder().encodeToString(decodedString.getBytes()));
	}

	protected String getBase64DecodedValue(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString));
	}
	
	protected String printStringInColor(String text, String color) {
		return "<Font color = " + color + " > " + text + "</Font>";
	}
}
