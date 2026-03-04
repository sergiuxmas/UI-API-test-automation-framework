package pageObjects;

import ui.BasePage;
import ui.UiEngine;

public class Checkout_Step_One extends BasePage {

    private static final String firstName = "//*[@id=\"first-name\"]";
    private static final String lastName = "//*[@id=\"last-name\"]";
    private static final String postalCode = "//*[@id=\"postal-code\"]";
    private static final String continueBtn = "//*[@id=\"continue\"]";

    public Checkout_Step_One(UiEngine ui) {
        super(ui);
    }

    public void open() {
        openAndWait("https://www.saucedemo.com/checkout-step-one.html", firstName, 10);
    }

    public void enterFirstName(String name) {
        ui.type("xpath", firstName, name);
    }

    public void enterLastName(String name) {
        ui.type("xpath", lastName, name);
    }

    public void enterZipCode(String zipCode) {
        ui.type("xpath", postalCode, zipCode);
    }

    public void clickContinueButton() {
        ui.click("xpath", continueBtn);
    }
}
