package ui;

public abstract class BasePage {
    protected final UiEngine ui;

    protected BasePage(UiEngine ui) {
        this.ui = ui;
    }

    protected void openAndWait(String url, String readyElement, int timeoutSec) {
        ui.open(url);
        ui.waitVisible("xpath", readyElement, timeoutSec);
    }
}
