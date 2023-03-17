package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
        public void test_Login(){

        logger.info("*** Starting TC_002_LoginTest ***");

            try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setTxtEmail(rb.getString("email"));
            lp.setTxtPassword(rb.getString("password"));
            lp.clickbtnLogin();

            MyAccountPage map = new MyAccountPage(driver);
            boolean result = map.cnfmyaccMessage();

            Assert.assertEquals(result,true,"Invalid Data Entered");

            map.setBtnLogout();

        } catch (Exception e) {

        Assert.fail();
    }
        logger.info("*** Finished TC_002_LoginTest");

        }
}
