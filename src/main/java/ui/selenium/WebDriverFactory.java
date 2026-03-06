package ui.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ReadConfigFile;

import java.net.URL;
import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver driver;

    public static void initWebDriver() {
        try {
            String browser = ReadConfigFile.readConfig("browser");
            String webSiteUrl = ReadConfigFile.readConfig("URL");
            String headless = ReadConfigFile.readConfigOrDefault("headless", "false").trim();

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    if (Boolean.parseBoolean(headless)) {
                        options.addArguments("--headless=new");
                    }
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--incognito");
                    options.addArguments("--window-size=1920,1080");
                    String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
                    if (remoteUrl != null && !remoteUrl.trim().isEmpty()) {
                        driver = new RemoteWebDriver(new URL(remoteUrl.trim()), options);
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(options);
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions());
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.get(webSiteUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initWebDriver();
        }
        return driver;
    }

    public static void tearDownDrivers() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
