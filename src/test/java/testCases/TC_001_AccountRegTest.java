package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC_001_AccountRegTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void account_Registration() {

        logger.debug("Application Logs....");

        logger.info("****Starting TC_001_AccountRegTest****");

        try {
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on My Account link");
        hp.clickRegister();
        logger.info("Clicked on Registration link");
        AccountRegPage ar = new AccountRegPage(driver);
        logger.info("Providing customer data");
        ar.setTxtFirstname(randomString().toUpperCase());
        ar.setTxtLastname(randomString().toUpperCase());
        ar.setTxtEmail(randomString()+"@email.com");
        ar.setTxtPassword(randomAlphaNumeric());
        ar.setChkdPolicy();
        ar.clickBtnContinue();
        logger.info("Clicked on Continue Button");
        String cnfmsg = ar.getMsgConfirmation();

        logger.info("Validating expected Message");

            Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
        } catch (Exception e) {

            Assert.fail();
        }
        logger.info("****Finished TC_001_AccountRegTest****");
    }


}
