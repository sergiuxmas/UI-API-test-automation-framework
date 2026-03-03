package pageObjects;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

@NoArgsConstructor
public class Checkout_Step_One extends DriverFactory {

    private final By firstName = By.xpath("//*[@id=\"first-name\"]");
    private final By lastName = By.xpath("//*[@id=\"last-name\"]");
    private final By postalCode = By.xpath("//*[@id=\"postal-code\"]");
    private final By continueBtn = By.xpath("//*[@id=\"continue\"]");

    public void open() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    public void enterFirstName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        firstNameEl.sendKeys(name);
    }

    public void enterLastName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement lastNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        lastNameEl.sendKeys(name);
    }

    public void enterZipCode(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement zipCodeEl = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));
        zipCodeEl.sendKeys(zipCode);
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        continueButton.click();
    }
}
