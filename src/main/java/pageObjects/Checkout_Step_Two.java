package pageObjects;


import org.testng.Assert;
import ui.BasePage;
import ui.UiEngine;

public class Checkout_Step_Two extends BasePage {

    private static final String summaryTax = "//div[@class='summary_tax_label']";
    private static final String summaryTotal = "//div[@class='summary_total_label']";

    public Checkout_Step_Two(UiEngine ui) {
        super(ui);
    }

    public void open() {
        openAndWait("https://www.saucedemo.com/checkout-step-two.html", summaryTax, 10);
    }

    public void checkTax(String expectedTax) {
        ui.waitVisible("xpath", summaryTax, 10);
        String actualTax = ui.text("xpath", summaryTax).trim();
        Assert.assertEquals(actualTax, expectedTax);
    }

    public void checkTotal(String expectedTax) {
        ui.waitVisible("xpath", summaryTotal, 10);
        String actualTax = ui.text("xpath", summaryTotal).trim();
        Assert.assertEquals(actualTax, expectedTax);
    }

}
