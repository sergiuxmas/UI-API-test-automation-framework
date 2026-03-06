package ui;

import ui.playwright.PlaywrightEngine;
import ui.selenium.SeleniumEngine;

public final class UiEngineFactory {
    private UiEngineFactory() {
    }

    public static UiEngine create() {
        String engine = System.getProperty("browser.engine", "playwright").trim().toLowerCase();
        switch (engine) {
            case "playwright":
                return new PlaywrightEngine();
            case "selenium":
                return new SeleniumEngine();
            default:
                throw new IllegalArgumentException("Unknown browser.engine=" + engine);
        }
    }
}
