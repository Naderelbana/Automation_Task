package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    private By cartIcon = By.className("shopping_cart_link");
    private By inventoryItems = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public int getProductsCount() {
        List<WebElement> products = driver.findElements(inventoryItems);
        return products.size();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}