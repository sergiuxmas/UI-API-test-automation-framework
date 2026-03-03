package pageObjects;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

public class Checkout_Step_Two extends DriverFactory {

    public Checkout_Step_Two() {
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    public void open() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    public void checkTax(String taxValue) {
        open();
        WebElement tax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_tax_label']")));
        Assert.assertEquals(tax.getText(), taxValue);
    }

    public void checkTotal(String totalValue) {
        open();
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_total_label']")));
        Assert.assertEquals(total.getText(), totalValue);
    }

}
