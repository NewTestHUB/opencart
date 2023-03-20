package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import testBase.BaseClass;

public class TC_006_EndToEndTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void test_EndtoEnd() throws InterruptedException {

        logger.info("*** Starting TC_006_EndToEndTest ***");

        SoftAssert myassert = new SoftAssert();

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegPage ar = new AccountRegPage(driver);
        ar.setTxtFirstname(randomString().toUpperCase());
        ar.setTxtLastname(randomString().toUpperCase());
        String email = randomAlphaNumeric()+"@email.com";
        ar.setTxtEmail(email);
        ar.setTxtPassword("Test@123");
        ar.setChkdPolicy();
        ar.clickBtnContinue();

        String accountCnfMsg = ar.getMsgConfirmation();
        System.out.println(accountCnfMsg);

        myassert.assertEquals(accountCnfMsg,"Your Account Has Been Created!");

        MyAccountPage map = new MyAccountPage(driver);
        map.setBtnLogout();

        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.setTxtEmail(email);
        lp.setTxtPassword("Test@123");
        lp.clickbtnLogin();

        myassert.assertEquals(map.cnfmyaccMessage(),true);

        hp.setBoxSearch("mac");
        hp.setBtnSearch();

        SearchPage sp = new SearchPage(driver);
        if(sp.isProductExist("MacBook")) {
            sp.selectProduct("MacBook");
            sp.setQuantity("2");
            sp.addToCart();
        }

        System.out.println("is product added to Cart? "+sp.checkCnfMsg());
        myassert.assertEquals(sp.checkCnfMsg(),true);
        Thread.sleep(10);

        ShoppingCartPage scp = new ShoppingCartPage(driver);

        scp.clickBtnItemToNavigateToCart();
        Thread.sleep(10);
        scp.clickLnkViewCart();
        System.out.println("Total shopping amount @Shopping Cart: "+scp.getTotalPrice());

        myassert.assertEquals(scp.getTotalPrice(),"$1,204.00");

        scp.clickBtnCheckout();

        CheckoutPage cp = new CheckoutPage(driver);

        myassert.assertEquals(cp.getFinalBillingPrice(), "$1,204.00");
        System.out.println("Total shopping cost @Checkout: "+cp.getFinalBillingPrice());

        myassert.assertAll();

        logger.info("*** Finished TC_006_EndToEndTest ***");

    }

}
