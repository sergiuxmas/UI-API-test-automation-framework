package stepDefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.*;

import java.util.List;

public class UI_OrdersStep {

    public LoginPage loginPage;
    public Inventory_Main_Page inventoryMainPage;
    public Cart_Page cartPage;
    public Checkout_Step_One checkoutStepOne;
    public Checkout_Step_Two checkoutStepTwo;

    public UI_OrdersStep() {
        this.loginPage = new LoginPage(Hooks.ui());
        this.inventoryMainPage = new Inventory_Main_Page(Hooks.ui());
        this.cartPage = new Cart_Page(Hooks.ui());
        this.checkoutStepOne = new Checkout_Step_One(Hooks.ui());
        this.checkoutStepTwo = new Checkout_Step_Two(Hooks.ui());
    }

    @When("The user enter a valid credentials")
    public void the_user_enter_a_valid_credentials() {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.sendLoginCredentials(username, password);
    }

    @And("The user clicks on login button")
    public void the_user_clicks_on_login_button() throws Exception {
        loginPage.clickLoginButton();
    }

    @And("open shopping cart")
    public void open_shopping_cart() {
        inventoryMainPage.open();
    }

    @When("clicks button to add item in cart")
    public void clicks_button_to_add_item_in_cart() throws Exception {
        inventoryMainPage.clickAddButton();
    }

    @Then("should be visible counter red badge on cart icon")
    public void should_be_visible_counter_red_badge_on_cart_icon() {
        inventoryMainPage.visibilityOfRedBadgeCounter();
    }

    @When("In inventory page select {int} items")
    public void in_inventpory_page_select_items(Integer n) throws Exception {
        inventoryMainPage.open();
        inventoryMainPage.selectItems(n);
    }

    @And("User clicks on shopping cart icon")
    public void user_clicks_on_shoping_cart_icon() {
        inventoryMainPage.open();
        inventoryMainPage.clickOnCart();
    }

    @Then("There should be {int} items in the shopping cart list")
    public void there_should_be_items_in_the_shopping_cart_list(Integer n) {
        cartPage.open();
        cartPage.checkNumberOfItems(cartPage.itemCount(), n);

    }

    @When("User selects")
    public void user_selects(io.cucumber.datatable.DataTable dataTable) {
        List<String> selectedItems = dataTable.asList();
        for (String itemName : selectedItems) {
            inventoryMainPage.addItemInCartByName(itemName);
        }

    }

    @And("The user clicks on shoping cart icon")
    public void the_user_clicks_on_shoping_cart_icon() {
        inventoryMainPage.clickOnCart();
    }

    @And("Clicks Checkout button")
    public void clicks_checkout_button() {
        cartPage.open();
        cartPage.clickCheckout();
    }

    @And("User enter {string}, {string} and {string}")
    public void user_enter_and(String firstName, String lastName, String zipCode) {
        checkoutStepOne.open();
        checkoutStepOne.enterFirstName(firstName);
        checkoutStepOne.enterLastName(lastName);
        checkoutStepOne.enterZipCode(zipCode);
    }

    @And("Clicks Continue button")
    public void clicks_Continue_button() {
        checkoutStepOne.open();
        checkoutStepOne.clickContinueButton();
    }

    @Then("Tax should be {string}")
    public void tax_should_be(String taxValue) {
        checkoutStepTwo.open();
        checkoutStepTwo.checkTax(taxValue);
    }

    @And("Total should be {string}")
    public void total_should_be(String totalValue) {
        checkoutStepTwo.checkTotal(totalValue);
    }

}
