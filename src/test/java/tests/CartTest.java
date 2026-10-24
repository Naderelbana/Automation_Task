package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

import java.util.List;
import java.util.Set;

public class CartTest extends BaseTest {

    // 1. Verify Social Links
    @Test(priority = 1)
    public void testSocialLinks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);

        String mainWindow = driver.getWindowHandle();

        // LinkedIn
        inventoryPage.clickLinkedin();
        switchToNewWindow(mainWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"), "LinkedIn URL is incorrect!");
        driver.close();
        driver.switchTo().window(mainWindow);

        // Facebook
        inventoryPage.clickFacebook();
        switchToNewWindow(mainWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"), "Facebook URL is incorrect!");
        driver.close();
        driver.switchTo().window(mainWindow);

        // X
        inventoryPage.clickTwitter();
        switchToNewWindow(mainWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("x.com") || driver.getCurrentUrl().contains("twitter"), "Twitter/X URL is incorrect!");
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    private void switchToNewWindow(String mainWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // 2. Verify Cart Is Empty
    @Test(priority = 2)
    public void testCartIsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();

        Assert.assertTrue(inventoryPage.isCartEmpty(), "Cart is not empty upon login!");
    }

    // 3. Add 3 Specific Products (Data-Driven)
    @Test(priority = 3)
    public void testAdd3ProductsDataDriven() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);

        List<String> expectedProducts = DataDriven.getCartProducts();
        for (String product : expectedProducts) {
            inventoryPage.addProductToCart(product);
        }

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        List<String> actualProducts = cartPage.getCartItemNames();

        Assert.assertEquals(actualProducts, expectedProducts, "Cart items or order do not match expected data!");
    }

    // 4. Remove One Product
    @Test(priority = 4)
    public void testRemoveOneProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);

        List<String> products = DataDriven.getCartProducts();
        for (String p : products) {
            inventoryPage.addProductToCart(p);
        }

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.removeBoltTShirt();

        // Verify remaining items and inventory button states
        driver.navigate().to("https://www.saucedemo.com/inventory.html");

        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Bolt T-Shirt"), "Add to cart", "Button did not change to Add to cart!");
        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Backpack"), "Remove", "Backpack button state changed incorrectly!");
        Assert.assertEquals(inventoryPage.getProductButtonText("Sauce Labs Onesie"), "Remove", "Onesie button state changed incorrectly!");
    }

    // 5. Verify Cart Total Price (Challenge)
    @Test(priority = 5)
    public void testCartTotalPrice() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);

        List<String> products = DataDriven.getCartProducts();
        double expectedSum = 0.0;

        for (String p : products) {
            expectedSum += inventoryPage.getProductPrice(p);
            inventoryPage.addProductToCart(p);
        }

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // ملء بيانات الـ Checkout السريعة عشان يوصل لصفحة الـ Overview
        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        double actualItemTotal = cartPage.getItemTotal();
        Assert.assertEquals(actualItemTotal, expectedSum, 0.01, "Calculated sum does not match checkout item total!");
    }

    // 6. Checkout With an Empty Cart (Challenge)
    @Test(priority = 6)
    public void testCheckoutEmptyCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html") || currentUrl.contains("checkout-step-one.html"),
                "Unexpected behavior when checking out with an empty cart!");
    }

    // 7. Cart State After Logout/Login (Challenge)
    @Test(priority = 7)
    public void testCartStateAfterLogoutLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));
        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");

        inventoryPage.logout();

        loginPage.login(DataDriven.jsonReader("validUsername"), DataDriven.jsonReader("validPassword"));

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertFalse(cartPage.isCartEmpty(), "Cart items were lost after logout and re-login!");
    }
}