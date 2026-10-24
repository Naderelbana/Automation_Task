package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    WebDriver driver;

    private final By cartItemsNames = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By itemTotalLabel = By.className("summary_subtotal_label");
    private final By removeBoltShirtButton = By.id("remove-sauce-labs-bolt-t-shirt");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(cartItemsNames);
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItemsNames);
        return items.isEmpty();
    }

    public void removeBoltTShirt() {
        driver.findElement(removeBoltShirtButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public double getItemTotal() {
        // نص الـ Item total بيبقى شكله كده: "Item total: $55.97"
        String text = driver.findElement(itemTotalLabel).getText();
        String numericValue = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericValue);
    }
}