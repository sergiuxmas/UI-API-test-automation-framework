package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/featureFiles"},
        glue = {"stepDefs"},
        dryRun = false,
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-html-report",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true
)
public class MainRunner extends AbstractTestNGCucumberTests {
}
