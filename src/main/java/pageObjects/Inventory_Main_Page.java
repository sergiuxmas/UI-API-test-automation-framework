package pageObjects;


import ui.BasePage;
import ui.UiEngine;

import static org.junit.Assert.assertTrue;

public class Inventory_Main_Page extends BasePage {

    private static final String redBadge = "//span[contains(@class,'shopping_cart_badge')]";
    private static final String inventoryContainer = "inventory_container";
    private static final String addButton = "//button[contains(@class,'btn_inventory') and normalize-space()='Add to cart']";
    private static final String addToCartBtn = "//button[@data-test='add-to-cart-sauce-labs-backpack']";
    private static final String inventoryItem = "./ancestor::div[contains(@class,'inventory_item')]";
    private static final String inventoryItemName = ".inventory_item_name";
    private static final String shoppingCart = "//a[contains(@class,'shopping_cart_link')]";

    public Inventory_Main_Page(UiEngine ui) {
        super(ui);
    }

    public void open() {
        openAndWait("https://www.saucedemo.com/inventory.html", shoppingCart, 10);
    }

    public void clickAddButton() {
//        ui.waitVisible("xpath", shoppingCart, 10);
        ui.waitVisible("xpath", addToCartBtn, 10);
        ui.click("xpath", addToCartBtn);
    }

    public void visibilityOfRedBadgeCounter() {
        assertTrue("Red badge is not visible", ui.isVisible("xpath", redBadge));
    }

    public void selectItems(int n) {
        // ensure inventory page is loaded
        ui.waitVisible("id", inventoryContainer, 10);
        for (int i = 0; i < n; i++) {
            int buttonsCount = ui.count("xpath", addButton);
            if (buttonsCount == 0) {
                throw new RuntimeException("No more 'Add to cart' buttons found.");
            }

            // We always click the first available button (index 0),
            // which mirrors your Selenium code.
            String dataTest = ui.attr("xpath", addButton, "data-test");

            // On SauceDemo the items are aligned by index: the first button belongs to first item, etc.
            // We read the first item name (index 0) to log.
            String itemName = ui.textAt("css", inventoryItemName, 0);
            System.out.println("Adding to cart: " + itemName + " | data-test=" + dataTest);
            ui.clickAt("xpath", addButton, 0);
        }
    }

    public void clickOnCart() {
        ui.click("xpath", shoppingCart);
    }

    public void addItemInCartByName(String itemName) {
        String xpath = String.format(
                "//div[contains(normalize-space(.), '%s')]" +
                        "/ancestor::div[contains(@class,'inventory_item')][1]" +
                        "//button[contains(@data-test,'add-to-cart') or contains(.,'Add to cart')]",
                itemName
        );
        ui.click("xpath", xpath);
    }
}

