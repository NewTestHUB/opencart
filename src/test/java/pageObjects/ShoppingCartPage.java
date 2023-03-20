package pageObjects;

import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{

    public ShoppingCartPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/button")
    WebElement btnItem;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/ul/li/div/p/a[1]")
    WebElement lnkViewCart;

    @FindBy(xpath = "//*[@id=\"checkout-total\"]/tr[4]/td[2]")
    WebElement lblTotalPrice;

    @FindBy(xpath = "//a[text()='Checkout']")
    WebElement btnCheckout;

    public void clickBtnItemToNavigateToCart() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", btnItem);

    }

    public void clickLnkViewCart() {

      JavascriptExecutor jse = (JavascriptExecutor) driver;
      jse.executeScript("arguments[0].click()", lnkViewCart);
    }

    public String getTotalPrice() {

        return lblTotalPrice.getText();
    }

    public void clickBtnCheckout() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", btnCheckout);
    }

}
