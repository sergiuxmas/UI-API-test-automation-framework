package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver driver;

    public static void initWebDriver() {
        try {
            String browser = ReadConfigFile.readConfig("browser");
            String webSiteUrl = ReadConfigFile.readConfig("URL");

            switch (browser.toLowerCase()) {

                case "chrome":
                    ChromeOptions options = new ChromeOptions();
//				options.addArguments("--headless=false");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444/wd/hub"), options);//wd/hub
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("marionette", true);
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.get(webSiteUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void tearDownDrivers() {
        if (driver != null) {
            driver.quit();
        }
    }

}
