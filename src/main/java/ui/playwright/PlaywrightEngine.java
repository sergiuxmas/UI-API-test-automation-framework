package ui.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import ui.UiEngine;
import utils.ReadConfigFile;

public class PlaywrightEngine implements UiEngine {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @Override
    public void start() {
        String browserName = ReadConfigFile.readConfig("browser").trim().toLowerCase();
        String url = ReadConfigFile.readConfig("URL").trim();
        boolean headless = Boolean.parseBoolean(ReadConfigFile.readConfigOrDefault("headless", "true"));
        int timeoutSeconds = Integer.parseInt(ReadConfigFile.readConfigOrDefault("timeoutSeconds", "30"));

        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);
        switch (browserName) {
            case "chrome":
                // Uses installed Chrome if available (no need for bundled chromium)
                launchOptions.setChannel("chrome");
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "edge":
            case "msedge":
                launchOptions.setChannel("msedge");
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "chromium":
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser in config.properties: " + browserName);
        }
        context = browser.newContext();
        page = context.newPage();
        page.setDefaultTimeout(timeoutSeconds * 1000.0);
        page.navigate(url);
    }

    @Override
    public void stop() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Override
    public void navigate(String url) {
        page.navigate(url);
    }

    @Override
    public void click(String locatorType, String locator) {
        page.locator(toPwSelector(locatorType, locator)).click();
    }

    @Override
    public void type(String locatorType, String locator, String text) {
        page.locator(toPwSelector(locatorType, locator)).fill(text);
    }

    @Override
    public String text(String locatorType, String locator) {
        return page.locator(toPwSelector(locatorType, locator)).innerText();
    }

    @Override
    public int count(String locatorType, String locator) {
        return page.locator(toPwSelector(locatorType, locator)).count();
    }

    @Override
    public void open(String url) {
        page.navigate(url);
        System.out.println("Current URL: " + page.url());
    }

    @Override
    public boolean isVisible(String locatorType, String locator) {
        return page.locator(toPwSelector(locatorType, locator)).isVisible();
    }

    @Override
    public void waitUrlIs(String url, int timeoutSeconds) {
        page.waitForURL(url, new Page.WaitForURLOptions().setTimeout(timeoutSeconds * 1000.0));
    }

    @Override
    public String attr(String locatorType, String locator, String attributeName) {
        String loc = toPwSelector(locatorType, locator);
        return page.locator(loc).first().getAttribute(attributeName);
    }

    @Override
    public void clickAt(String locatorType, String locator, int index) {
        String loc = toPwSelector(locatorType, locator);
        page.locator(loc).nth(index).click();
    }

    @Override
    public String textAt(String locatorType, String locator, int index) {
        String loc = toPwSelector(locatorType, locator);
        return page.locator(loc).nth(index).innerText();
    }

    @Override
    public String currentUrl() {
        return page.url();
    }

    @Override
    public void waitVisible(String selectorType, String selector, int timeoutSeconds) {
        String pwSelector = toPwSelector(selectorType, selector);
        page.locator(pwSelector)
                .first()
                .waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(timeoutSeconds * 1000.0));
    }

    private String toPwSelector(String locatorType, String locator) {
        String t = locatorType.trim().toLowerCase();
        switch (t) {
            case "css":
            case "cssselector":
                return locator;                  // plain CSS
            case "xpath":
                return "xpath=" + locator;       // explicit XPath
            case "id":
                return "#" + locator;            // CSS id
            case "name":
                return "[name='" + locator + "']";
            default:
                throw new IllegalArgumentException("Unsupported locatorType: " + locatorType);
        }
    }
}
