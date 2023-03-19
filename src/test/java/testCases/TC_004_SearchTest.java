package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void test_SearchProduct() {

        logger.info("*** Starting TC_004_SearchTest ***");

        try {
        HomePage hp = new HomePage(driver);
        hp.setBoxSearch("ipod");
        hp.setBtnSearch();

        SearchPage sp = new SearchPage(driver);
        boolean result = sp.isProductExist("iPod Shuffle");

        Assert.assertEquals(result,true);

        sp.selectProduct("iPod Shuffle");


        } catch (Exception e) {

            Assert.fail();
        }

        logger.info("*** Finished TC_004_SearchTest ***");
    }

}
