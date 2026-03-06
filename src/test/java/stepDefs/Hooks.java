package stepDefs;

import ApiUtils.RestAssuredExtension;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui.UiEngine;
import ui.UiEngineFactory;

public class Hooks {
    public static ThreadLocal<UiEngine> ENGINE = new ThreadLocal<>();
    public static final ThreadLocal<RestAssuredExtension> API = new ThreadLocal<>();

    @Before("@back-end")
    public void TestSetup() {
        API.set(new RestAssuredExtension());
    }

    @Before("@front-end")
    public void uiSetup() {
        String sysProp = System.getProperty("browser.engine");
        System.out.println("browser.engine sysprop = " + sysProp);

        UiEngine engine = UiEngineFactory.create();
        ENGINE.set(engine);

        try {
            engine.start();
            System.out.println("UI Engine started: " + engine.getClass().getSimpleName());
        } catch (RuntimeException e) {
            safeStop(engine);
            ENGINE.remove();
            throw e;
        }
    }

    @After("@front-end")
    public void uiTearDown() {
        UiEngine engine = ENGINE.get();
        safeStop(engine);
        ENGINE.remove();
    }

    @After("@back-end")
    public void apiTearDown() {
        API.remove();
    }

    public static UiEngine ui() {
        UiEngine e = ENGINE.get();
        if (e == null) {
            throw new IllegalStateException(
                    "UI engine is not initialized for this thread. "
                            + "Are you calling Hooks.ui() from a @back-end scenario or missing @front-end tag?"
            );
        }
        return e;
    }

    private static void safeStop(UiEngine engine) {
        if (engine == null) return;
        try {
            engine.stop();
        } catch (Exception ex) {
            System.err.println("Warning: engine.stop() failed: " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
}
