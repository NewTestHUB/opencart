package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver)  {
        super(driver);
    }
    // Elements
    @FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span")
    WebElement lnkMyAccount;

    @FindBy(linkText = "Register")
    WebElement lnkRegister;

    @FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[2]/a")
    WebElement lnkLogin;

    @FindBy(name = "search")
    WebElement txtSearchBox;

    @FindBy(xpath = "//*[@id=\"search\"]/button/i")
    WebElement btnSearch;

    //Action Methods

    public void clickMyAccount() {

        lnkMyAccount.click();
    }
    public void clickRegister() {
        lnkRegister.click();
    }
    public void clickLogin() {
        lnkLogin.click();
    }

    public void setBoxSearch(String pName){
        txtSearchBox.sendKeys(pName);
    }
    public void setBtnSearch() {
        btnSearch.click();
    }

}
