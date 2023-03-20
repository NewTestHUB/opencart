package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"checkout-confirm\"]/div[1]/table/tfoot/tr[4]/td[2]")
    WebElement lblBillingPrice;

    @FindBy(xpath = "//*[@id=\"checkout-payment\"]/div/button")
    WebElement btnConfirmOrder;

    public String getFinalBillingPrice() {

        return lblBillingPrice.getText();
    }

    public void clickBtnConfirmOrder() {

        btnConfirmOrder.click();
    }
}
