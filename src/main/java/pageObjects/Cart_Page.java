package pageObjects;

import org.junit.Assert;
import ui.BasePage;
import ui.UiEngine;

public class Cart_Page extends BasePage {

    private static final String cartItem = "//div[@data-test='cart-list']//div[contains(@class,'cart_item_label')]";
    private static final String checkoutBtn = "//*[@id=\"checkout\"]";

    public Cart_Page(UiEngine ui) {
        super(ui);
    }

    public void open() {
        openAndWait("https://www.saucedemo.com/cart.html", checkoutBtn, 10);
    }

    public int itemCount() {
        return ui.count("xpath", cartItem);
    }

    public void checkNumberOfItems(int actual, int expected) {
        try {
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickCheckout() {
        ui.click("xpath", checkoutBtn);
    }

}
