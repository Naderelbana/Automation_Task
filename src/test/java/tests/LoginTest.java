package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String username = DataDriven.jsonReader("validUsername");
        String password = DataDriven.jsonReader("validPassword");

        loginPage.login(username, password);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/inventory.html"), "Login failed, not redirected to inventory page!");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String username = DataDriven.jsonReader("invalidUsername");
        String password = DataDriven.jsonReader("invalidPassword");

        loginPage.login(username, password);

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Username and password do not match"), "Error message is incorrect!");
    }

    @Test(priority = 3)
    public void testLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        String username = DataDriven.jsonReader("validUsername");

        loginPage.login(username, "");

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Password is required"), "Error message for missing password is incorrect!");
    }
}