package pageObjects;

import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.util.List;

@NoArgsConstructor
public class Cart_Page extends DriverFactory {

    private final By cartItem = By.xpath("//div[@data-test='cart-list']//div[contains(@class,'cart_item_label')]");
    private final By checkoutBtn = By.xpath("//*[@id=\"checkout\"]");

    public void open() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://www.saucedemo.com/cart.html");
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public int itemCount() {
        final List<WebElement> cartItems = driver.findElements(cartItem);
        return cartItems.size();
    }

    public void checkNumberOfItems(int actual, int expected) {
        try {
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        checkoutButton.click();
    }

}
