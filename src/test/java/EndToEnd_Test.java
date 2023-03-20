import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.AbstractSet;
import java.util.List;

public class EndToEnd_Test {

    public static WebDriver driver;
//    @Test
//    public void test_EndToEnd() {

        public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://localhost/opencart/upload/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.name("firstname")).sendKeys("Test101");
        driver.findElement(By.name("lastname")).sendKeys("Customer");
        driver.findElement(By.name("email")).sendKeys(RandomStringUtils.randomAlphabetic(5) +"@email.com");
        driver.findElement(By.name("password")).sendKeys("Test@123");
        WebElement chkBox = driver.findElement(By.name("agree"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",chkBox);

        WebElement btnContinue = driver.findElement(By.xpath("//button[text()='Continue']"));
        jse.executeScript("arguments[0].click()", btnContinue);

        WebElement regCnfMsg = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"));

        try {
            Assert.assertEquals(regCnfMsg.getText(),"Your Account Has Been Created!");
        } catch (Exception e) {

            e.getMessage();
        }

        driver.findElement(By.name("search")).sendKeys("mac");

        driver.findElement(By.xpath("//*[@id=\"search\"]/button")).click();

        List<WebElement> searchProducts = driver.findElements(By.xpath("//*[@id=\"product-list\"]"));

        for(WebElement products:searchProducts) {
            if(products.getText().contains("MacBook"))
                products.click();
        }

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("2");

        WebElement btnCart = driver.findElement(By.id("button-cart"));
        jse.executeScript("arguments[0].click()", btnCart);

        WebElement msgCart=driver.findElement(By.xpath("//*[@id=\"alert\"]/div"));

        try {
            Assert.assertEquals(msgCart.isDisplayed(),true);
        } catch (Exception e){
            e.getMessage();
        }

        WebElement btnItem = driver.findElement(By.xpath("//*[@id=\"header-cart\"]/div/button"));
        jse.executeScript("arguments[0].click()",btnItem);

        WebElement btnViewCart = driver.findElement(By.xpath("//*[@id=\"header-cart\"]/div/ul/li/div/p/a[1]"));
        jse.executeScript("arguments[0].click()", btnViewCart);

        WebElement costinCart = driver.findElement(By.xpath("//*[@id=\"checkout-total\"]/tr[4]/td[2]"));
        System.out.println("Cart Value in : "+costinCart.getText());

        WebElement btnCheckout = driver.findElement(By.xpath("//a[text()='Checkout']"));
        jse.executeScript("arguments[0].click()", btnCheckout);



//        driver.close();

    }

}
