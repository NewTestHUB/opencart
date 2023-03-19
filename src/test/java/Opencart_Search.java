import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.WebElement;

import javax.swing.text.TabableView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.List;

public class Opencart_Search {

    public static WebDriver driver;
    static String item = "ipod";

//    @Test
//    public void test_Search () throws IOException {
    public static void main(String[] args) throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost/opencart/upload/");

        driver.findElement(By.name("search")).sendKeys(item);
        driver.findElement(By.xpath("//*[@id=\"search\"]/button/i")).click();

        File searchPage = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(searchPage,new File("./screenshot/SearchPage.png"));

//        WebElement product = driver.findElement(By.xpath("//*[@id=\"compare-total\"]/span"));
//
//        String pName = product.getText();
//
//        System.out.println(pName);
//
//        try {
//            Assert.assertEquals(pName, "Product Compare (0)", "Searched item not available");
//        } catch (Exception e) {
//        e.getCause();
//    }

        List<WebElement> searchProducts = driver.findElements(By.xpath("//*[@id='content']/div[5]"));


       for(WebElement product:searchProducts) {

           if(product.getText().contains("iPod Shuffle")){
               product.click();
           }

       }
       driver.findElement(By.xpath("//*[@id=\"input-quantity\"]")).clear();
        driver.findElement(By.name("quantity")).sendKeys("2");
       WebElement btnAddCart=driver.findElement(By.xpath("//*[@id=\"button-cart\"]"));

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", btnAddCart);

       WebElement cnfMsg = driver.findElement(By.xpath("//div[contains(text(),'Success: You have added')]"));

       try {

           Assert.assertEquals(cnfMsg.isDisplayed(),true, "Product not added to Cart");
       } catch (Exception e) {
           e.getMessage();
       }


        driver.close();
    }
}
