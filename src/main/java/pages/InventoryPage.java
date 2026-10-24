package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    // Locators
    private final By cartIcon = By.className("shopping_cart_link");
    private final By inventoryItems = By.className("inventory_item");
    private final By linkedinIcon = By.cssSelector(".social_linkedin a");
    private final By facebookIcon = By.cssSelector(".social_facebook a");
    private final By twitterIcon = By.cssSelector(".social_x a");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
    public boolean isCartEmpty(){
        String cartEmpty = driver.findElement(cartIcon).getAttribute("aria-label");
        return cartEmpty.equals("Cart, empty");
    }

    public int getProductsCount() {
        List<WebElement> products = driver.findElements(inventoryItems);
        return products.size();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void addProductToCart(String productName) {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public double getProductPrice(String productName) {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                String priceText = item.findElement(By.className("inventory_item_price")).getText(); // زي "$29.99"
                return Double.parseDouble(priceText.replace("$", ""));
            }
        }
        return 0.0;
    }

    public String getProductButtonText(String productName) {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                return item.findElement(By.tagName("button")).getText();
            }
        }
        return "";
    }

    // Social Links clicks
    public void clickLinkedin() { driver.findElement(linkedinIcon).click(); }
    public void clickFacebook() { driver.findElement(facebookIcon).click(); }
    public void clickTwitter() { driver.findElement(twitterIcon).click(); }

    // Logout action
    public void logout() {
        driver.findElement(burgerMenu).click();
        try { Thread.sleep(1000); } catch (Exception e) {}
        driver.findElement(logoutLink).click();
    }
}