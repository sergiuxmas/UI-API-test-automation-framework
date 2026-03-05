package pageObjects;

import org.testng.Assert;
import ui.BasePage;
import ui.UiEngine;

public class LoginPage extends BasePage {

    private static final String textInputUserName = "//input[@id='user-name']";
    private static final String textInputPass = "//input[@id='password']";
    private static final String btnLogin = "//input[@class='submit-button btn_action']";
    private static final String errorBtn = "//button[@class='error-button']";

    public LoginPage(UiEngine ui) {
        super(ui);
    }

    public void open() {
        openAndWait("https://www.saucedemo.com/", btnLogin, 10);
    }

    public void sendLoginCredentials(String username, String password) {
        ui.type("xpath", textInputUserName, username);
        ui.type("xpath", textInputPass, password);
    }

    public void clickLoginButton() throws Exception {
        ui.click("xpath", btnLogin);
    }

    public void visibilityOfInvalidCredentialsMsg() {
        Assert.assertTrue(ui.isVisible("xpath", errorBtn), "Invalid credentials message is not visible");
    }

    public void accessToInventoryPage() {
        String expected = "https://www.saucedemo.com/inventory.html";
        ui.waitUrlIs(expected, 10);
        Assert.assertEquals(ui.currentUrl(), expected);
    }
}
