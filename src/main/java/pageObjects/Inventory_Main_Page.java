package pageObjects;


import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;

@NoArgsConstructor
public class Inventory_Main_Page extends DriverFactory {

    private final By redBadge = By.xpath("//span[contains(@class,'shopping_cart_badge')]");
    private final By inventoryContainer = By.id("inventory_container");
    private final By addButton = By.xpath("//button[contains(@class,'btn_inventory') and normalize-space()='Add to cart']");
    private final By inventoryItem = By.xpath("./ancestor::div[contains(@class,'inventory_item')]");
    private final By inventoryItemName = By.cssSelector(".inventory_item_name");

    public void open() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public void clickAddButton() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement btnAddToCart1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']")));
        btnAddToCart1.click();
    }

    public void visibilityOfRedBadgeCounter() {
        WebElement redBadgeCounter = driver.findElement(redBadge);
        try {
            assertTrue(redBadgeCounter.isDisplayed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectItems(int n) {
        // make sure inventory page is loaded
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));

        for (int i = 0; i < n; i++) {
            List<WebElement> addButtons = driver.findElements(addButton);

            if (addButtons.isEmpty()) {
                throw new RuntimeException("No more 'Add to cart' buttons found.");
            }

            WebElement btn = addButtons.get(0);

            // 1) Log the locator-ish identifier (data-test)
            String dataTest = btn.getAttribute("data-test"); // e.g. add-to-cart-sauce-labs-backpack

            // 2) Log the product name (optional but very useful)
            // Button is inside an inventory item; find the item name within the same item container
            WebElement itemRoot = btn.findElement(inventoryItem);
            String itemName = itemRoot.findElement(inventoryItemName).getText();

            System.out.println("Adding to cart: " + itemName + " | data-test=" + dataTest);

            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        }
    }

    public void clickOnCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'shopping_cart_link')]")));
        cart.click();
    }

    public void addItemInCartByName(String ItemName) {
        String xpath = String.format("//div[contains(., '%s')]/parent::a/parent::div/following-sibling::div/button", ItemName);
        WebElement itemElement = driver.findElement(By.xpath(xpath));
        itemElement.click();
    }
}

