package ui;

public interface UiEngine {
    void start();

    void stop();

    void navigate(String url);

    void click(String locatorType, String locator);      // locator as CSS for both (recommended)

    void type(String locatorType, String locator, String text);

    String text(String locatorType, String locator);

    int count(String locatorType, String locator);

    void open(String url);

    String currentUrl();

    void waitVisible(String selectorType, String selector, int timeoutSeconds);

    boolean isVisible(String locatorType, String locator);

    void waitUrlIs(String url, int timeoutSeconds);

    String attr(String locatorType, String locator, String attributeName);

    void clickAt(String locatorType, String locator, int index); // 0-based

    String textAt(String locatorType, String locator, int index); // 0-based
}
