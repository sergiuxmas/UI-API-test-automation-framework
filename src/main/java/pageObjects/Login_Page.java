package pageObjects;

import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import static org.junit.Assert.assertTrue;

@NoArgsConstructor
public class Login_Page extends DriverFactory {

    private final By textInputUserName = By.xpath("//input[@id='user-name']");
    private final By textInputPass = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@class='submit-button btn_action']");
    private final By errorBtn = By.xpath("//button[@class='error-button']");

    public void open() {
        driver.navigate().to("https://www.saucedemo.com/");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
    }

    public void sendLoginCredentials(String username, String password) {
        driver.findElement(textInputUserName).sendKeys(username);
        driver.findElement(textInputPass).sendKeys(password);
    }

    public void clickLoginButton() throws Exception {
        driver.findElement(btnLogin).click();
    }

    public void visibilityOfInvalidCredentialsMsg() {
        WebElement btnError = driver.findElement(errorBtn);
        assertTrue(btnError.isDisplayed());
    }

    public void accessToInventoryPage() {
        String CurrentUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals("https://www.saucedemo.com/inventory.html", CurrentUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
