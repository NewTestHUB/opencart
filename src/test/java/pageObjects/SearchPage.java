package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{

    public SearchPage(WebDriver driver) {

        super(driver);
    }

    //Elements
    @FindBy (xpath = "//*[@id='content']/div[5]")
    List<WebElement> searchProducts;

    @FindBy(name = "quantity")
    WebElement txtQty;

    @FindBy(xpath = "//button[text()='Add to Cart']")
    WebElement btnAddtoCard;

    @FindBy(xpath = "//div[contains(text(),'Success: You have added')]")
    WebElement cnfMsg;

    //Action

    public boolean isProductExist(String productName) {

        boolean flag = false;
        for (WebElement product:searchProducts){
            if(product.getText().contains(productName)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    public void selectProduct(String productName) {

        for(WebElement product:searchProducts){

            if(product.getText().contains(productName)){
                product.click();
            }
        }
    }

    public void setQuantity(String qty){
        txtQty.clear();
        txtQty.sendKeys(qty);
    }

    public void addToCart() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", btnAddtoCard);
    }

    public boolean checkCnfMsg() {

        try {
            return (cnfMsg.isDisplayed());
        } catch (Exception e){
            return false;
        }
    }


}
