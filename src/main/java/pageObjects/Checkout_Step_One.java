package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

public class Checkout_Step_One extends DriverFactory {

    public Checkout_Step_One() {
    }

    public void open() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    public void enterFirstName(String firstName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"first-name\"]")));
        firstNameEl.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement lastNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"last-name\"]")));
        lastNameEl.sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement zipCodeEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"postal-code\"]")));
        zipCodeEl.sendKeys(zipCode);
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"continue\"]")));
        continueButton.click();
    }
}
