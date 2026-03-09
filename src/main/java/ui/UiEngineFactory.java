package ui;

import ui.playwright.PlaywrightEngine;
import ui.selenium.SeleniumEngine;

public final class UiEngineFactory {
    private UiEngineFactory() {
    }

    public static UiEngine create() {
        String engine = System.getProperty("browser.engine");
        if (engine == null || engine.isBlank()) {
            engine = "playwright";
        }
        engine = engine.toLowerCase();

        return switch (engine) {
            case "playwright" -> new PlaywrightEngine();
            case "selenium" -> new SeleniumEngine();
            default -> throw new IllegalArgumentException("Unknown browser.engine: " + engine);
        };
    }
}
