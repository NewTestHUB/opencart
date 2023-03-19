package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_005_AddToCartTest extends BaseClass {

    @Test
    public void test_addToCart() {

        logger.info("*** Starting TC_005_AddToCartTest ***");

        try {
            HomePage hp = new HomePage(driver);
            hp.setBoxSearch("ipod");
            hp.setBtnSearch();

            SearchPage sp = new SearchPage(driver);
            sp.selectProduct("iPod Shuffle");
            sp.setQuantity("3");
            sp.addToCart();

            Assert.assertEquals(sp.checkCnfMsg(), true);

        } catch (Exception E) {

            Assert.fail();
        }

        logger.info("*** Finished TC_005_AddToCartTest ***");

    }

}
