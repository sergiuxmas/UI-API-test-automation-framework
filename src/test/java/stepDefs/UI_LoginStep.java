package stepDefs;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

import java.util.List;


public class UI_LoginStep {

    public LoginPage loginPage;

    public UI_LoginStep() {
        this.loginPage = new LoginPage(Hooks.ui());
    }

    @Given("User is on login page")
    public void user_is_on_login_page() {
        System.out.println("Lunching browser and site https://www.saucedemo.com/ ");
        loginPage.open();
    }

    @When("User enters invalid username or invalid password")
    public void user_enters_invalid_username_or_invalid_password(DataTable credentials) throws Exception {
        List<List<String>> data = credentials.asLists();
        String username = data.get(1).get(0);
        String password = data.get(1).get(1);
        loginPage.sendLoginCredentials(username, password);
    }

    @And("User clicks on login button")
    public void user_clicks_on_login_button() throws Exception {
        loginPage.clickLoginButton();
    }

    @Then("Should be visible message for incorrect credentials")
    public void should_be_visible_message_for_incorrect_credentials() throws Exception {
        loginPage.visibilityOfInvalidCredentialsMsg();
    }

    @When("User enter a valid credentials")
    public void user_enter_a_valid_credentials() {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.sendLoginCredentials(username, password);
    }

    @And("Clicks on login button")
    public void clicks_on_login_button() throws Exception {
        loginPage.clickLoginButton();
    }

    @Then("User should login successfully and get access to inventory page")
    public void user_should_login_successfully_and_get_access_to_inventory_page() {
        loginPage.accessToInventoryPage();
    }

}

	
