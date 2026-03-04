package stepDefs;

import ApiUtils.RestAssuredExtension;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui.UiEngine;
import ui.UiEngineFactory;

public class Hooks {
    public static ThreadLocal<UiEngine> ENGINE = new ThreadLocal<>();

    @Before("@back-end")
    public void TestSetup() {
        RestAssuredExtension RestAssuredExtension = new RestAssuredExtension();
    }

    @Before("@front-end")
    public void beforeScenario() {
        UiEngine engine = UiEngineFactory.create();
        engine.start();
        ENGINE.set(engine);
        System.out.println("UI Engine started: " + engine.getClass().getSimpleName());
    }

    @After("@front-end")
    public void afterScenario() {
        UiEngine engine = ENGINE.get();
        if (engine != null) engine.stop();
        ENGINE.remove();
    }

    public static UiEngine ui() {
        return ENGINE.get();
    }
}
