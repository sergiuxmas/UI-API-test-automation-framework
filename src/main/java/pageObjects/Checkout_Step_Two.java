package pageObjects;


import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

@NoArgsConstructor
public class Checkout_Step_Two extends DriverFactory {

    private final By summaryTax = By.xpath("//div[@class='summary_tax_label']");
    private final By summaryTotal = By.xpath("//div[@class='summary_total_label']");

    public void open() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }

    public void checkTax(String taxValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement tax = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTax));
        Assert.assertEquals(tax.getText(), taxValue);
    }

    public void checkTotal(String totalValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTotal));
        Assert.assertEquals(total.getText(), totalValue);
    }

}
