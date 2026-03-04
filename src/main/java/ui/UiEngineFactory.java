package ui;

import lombok.NoArgsConstructor;
import ui.playwright.PlaywrightEngine;
import ui.selenium.SeleniumEngine;

@NoArgsConstructor
public final class UiEngineFactory {

    public static UiEngine create() {
        String engine = System.getProperty("browser.engine", "playwright").toLowerCase();
        switch (engine) {
            case "playwright":
                return new PlaywrightEngine();
            case "selenium":
            default:
                return new SeleniumEngine();
        }
    }
}
