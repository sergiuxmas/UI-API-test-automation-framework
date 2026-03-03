package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.util.List;


public class Cart_Page extends DriverFactory {

    public Cart_Page() {
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    public void open() {
        driver.get("https://www.saucedemo.com/cart.html");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public int itemCount() {

        open();
        final List<WebElement> cartItems = driver.findElements(By.xpath("//div[@data-test='cart-list']//div[contains(@class,'cart_item_label')]"));
        return cartItems.size();
    }


    public void checkNumberOfItems(int actual, int expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickCheckout() {
        open();
        WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout\"]")));
        checkoutButton.click();
    }

}
