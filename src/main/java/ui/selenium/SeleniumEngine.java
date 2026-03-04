package ui.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.UiEngine;

import java.time.Duration;
import java.util.List;

public class SeleniumEngine implements UiEngine {
    private WebDriver driver;
    private WebDriverWait wait;

    @Override
    public void start() {
        driver = WebDriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public void stop() {
        WebDriverFactory.tearDownDrivers();
        driver = null;
        wait = null;
    }

    @Override
    public void navigate(String url) {
        getDriver().get(url);
    }

    @Override
    public void click(String locatorType, String locator) {
        By by = toBy(locatorType, locator);
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Override
    public void type(String locatorType, String locator, String text) {
        By by = toBy(locatorType, locator);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        el.clear();
        el.sendKeys(text);
    }

    @Override
    public String text(String locatorType, String locator) {
        By by = toBy(locatorType, locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    @Override
    public int count(String locatorType, String locator) {
        // optionally wait for presence of at least the container before counting
        By by = toBy(locatorType, locator);
        return getDriver().findElements(by).size();
    }

    @Override
    public void open(String url) {
        getDriver().navigate().to(url);
        System.out.println("Current URL: " + getDriver().getCurrentUrl());
    }

    @Override
    public void waitVisible(String selectorType, String selector, int timeoutSeconds) {
        By by = toBy(selectorType, selector);
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Override
    public boolean isVisible(String locatorType, String locator) {
        By by = toBy(locatorType, locator);
        try {
            List<WebElement> els = getDriver().findElements(by);
            return !els.isEmpty() && els.get(0).isDisplayed();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

    @Override
    public void waitUrlIs(String url, int timeoutSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.urlToBe(url));
    }

    @Override
    public String attr(String locatorType, String locator, String attributeName) {
        By by = toBy(locatorType, locator);
        List<WebElement> elements = getDriver().findElements(by);
        if (elements.isEmpty()) throw new RuntimeException("No element found for: " + locator);
        return elements.get(0).getAttribute(attributeName);
    }

    @Override
    public void clickAt(String locatorType, String locator, int index) {
        By by = toBy(locatorType, locator);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> elements = getDriver().findElements(by);
        if (elements.size() <= index) throw new RuntimeException("Index out of range: " + index);
        WebElement el = elements.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    @Override
    public String textAt(String locatorType, String locator, int index) {
        By by = toBy(locatorType, locator);
        List<WebElement> elements = getDriver().findElements(by);
        if (elements.size() <= index) throw new RuntimeException("Index out of range: " + index);
        return elements.get(index).getText();
    }

    @Override
    public String currentUrl() {
        return getDriver().getCurrentUrl();
    }

    private By toBy(String selectorType, String selector) {
        if (selectorType == null) {
            throw new IllegalArgumentException("selectorType is null");
        }

        switch (selectorType.trim().toLowerCase()) {
            case "xpath":
                return By.xpath(selector);
            case "css":
            case "cssselector":
                return By.cssSelector(selector);
            case "id":
                return By.id(selector);
            case "name":
                return By.name(selector);
            case "class":
            case "classname":
                return By.className(selector);
            case "tag":
            case "tagname":
                return By.tagName(selector);
            case "linktext":
                return By.linkText(selector);
            case "partiallinktext":
                return By.partialLinkText(selector);
            default:
                throw new IllegalArgumentException("Unsupported selector type: " + selectorType);
        }
    }

    private WebDriver getDriver() {
        if (driver == null) {
            start();
        }
        return driver;
    }
}
