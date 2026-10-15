package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTest {

    @Test
    public void testInventoryPageElements() {
        LoginPage loginPage = new LoginPage(driver);
        String username = DataDriven.jsonReader("validUsername");
        String password = DataDriven.jsonReader("validPassword");

        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);

        String expectedTitle = "Swag Labs";
        Assert.assertEquals(inventoryPage.getPageTitle(), expectedTitle, "Page title does not match!");

        Assert.assertTrue(inventoryPage.isCartDisplayed(), "Cart icon is not displayed!");

        int expectedProductsCount = 6;
        Assert.assertEquals(inventoryPage.getProductsCount(), expectedProductsCount, "Products count is not 6!");
    }
}