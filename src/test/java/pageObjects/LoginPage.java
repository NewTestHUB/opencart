package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }
    //Elements

    @FindBy(name = "email")
    WebElement txtEmail;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//*[@id=\"form-login\"]/button")
    WebElement btnLogin;

    //Action Methods

    public void setTxtEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickbtnLogin() {
        btnLogin.click();
    }

}
