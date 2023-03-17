package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    //Elements

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement myaccMessage;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement btnLogout;

    //Action Methods

    public boolean cnfmyaccMessage() {

        try {
            return (myaccMessage.isDisplayed());
        } catch (Exception e) {
          return (false);
        }
    }

    public void setBtnLogout() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", btnLogout);
    }


}
