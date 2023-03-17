package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class AccountRegPage extends BasePage {

    public AccountRegPage(WebDriver driver) {

        super(driver);
    }

    //Elements
    @FindBy(name = "firstname")
    WebElement txtFirstname;

    @FindBy(name = "lastname")
    WebElement txtLastname;

    @FindBy(id = "input-email")
    WebElement txtEmail;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//*[@id=\"form-register\"]/div/div/button")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    //Action Methods

    public void setTxtFirstname(String firstname) {
        txtFirstname.sendKeys(firstname);    }

    public void setTxtLastname(String lastname) {

        txtLastname.sendKeys(lastname);
    }
    public void setTxtEmail(String email) {

        txtEmail.sendKeys(email);
    }
    public void setTxtPassword(String password) {

        txtPassword.sendKeys(password);
    }
    public void setChkdPolicy() {

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",chkdPolicy);
    }
    public void clickBtnContinue() {

//        btnContinue.click();

//        btnContinue.submit();

//        Actions act = new Actions(driver);
//        act.moveToElement(btnContinue).click().perform();

//        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", btnContinue);
    }
    public String getMsgConfirmation() {
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
