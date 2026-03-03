package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Inventory_Main_Page extends DriverFactory {

    public Inventory_Main_Page() {
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    public void clickAddButton() throws Exception {
        WebElement btnAddToCart1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']")));
        btnAddToCart1.click();
    }

    public void visibilityOfRedBadgeCounter() {
        WebElement redBadgeCounter = driver.findElement(By.xpath("//span[contains(@class,'shopping_cart_badge')]"));
        try {
            assertTrue(redBadgeCounter.isDisplayed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectItems(int n) {
        // make sure inventory page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

        for (int i = 0; i < n; i++) {
            List<WebElement> addButtons = driver.findElements(
                    By.xpath("//button[contains(@class,'btn_inventory') and normalize-space()='Add to cart']")
            );

            if (addButtons.isEmpty()) {
                throw new RuntimeException("No more 'Add to cart' buttons found.");
            }

            WebElement btn = addButtons.get(0);

            // 1) Log the locator-ish identifier (data-test)
            String dataTest = btn.getAttribute("data-test"); // e.g. add-to-cart-sauce-labs-backpack

            // 2) Log the product name (optional but very useful)
            // Button is inside an inventory item; find the item name within the same item container
            WebElement itemRoot = btn.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));
            String itemName = itemRoot.findElement(By.cssSelector(".inventory_item_name")).getText();

            System.out.println("Adding to cart: " + itemName + " | data-test=" + dataTest);

            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        }
    }

    public void clickOnCart() {
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'shopping_cart_link')]")));
        cart.click();
    }

    public void addItemInCartByName(String ItemName) {
        String xpath = String.format("//div[contains(., '%s')]/parent::a/parent::div/following-sibling::div/button", ItemName);
        WebElement itemElement = driver.findElement(By.xpath(xpath));
        itemElement.click();
    }
}

