package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

import javax.xml.crypto.Data;

public class TC_003_LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void test_LoginDDT(String email, String password, String exp) {

        logger.info("*** Starting TC_003_LoginDataDrivenTest ***");

        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setTxtEmail(email);
            lp.setTxtPassword(password);
            lp.clickbtnLogin();

            MyAccountPage map = new MyAccountPage(driver);
            boolean targetPage = map.cnfmyaccMessage();

            if (exp.equals("Valid")) {
                if (targetPage == true) {
                    map.setBtnLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equals("Invalid")) {
                if (targetPage == true) {
                    map.setBtnLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {

            Assert.fail();
        }
        logger.info("*** Finished TC_003_LoginDataDrivenTest ***");
    }
}
