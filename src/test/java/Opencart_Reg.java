import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import testBase.BaseClass;

import java.time.Duration;

public class Opencart_Reg extends BaseClass {


    public static void main(String [] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://localhost/opencart/upload");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        BaseClass bc = new BaseClass();

        driver.findElement(By.name("firstname")).sendKeys(bc.randomString());
        driver.findElement(By.name("lastname")).sendKeys(bc.randomString());
        driver.findElement(By.name("email")).sendKeys(bc.randomString()+"@email.com");
        driver.findElement(By.name("password")).sendKeys("Test@123");


        WebElement msg = driver.findElement(By.name("agree"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", msg);

        WebElement regbtn = driver.findElement(By.xpath("//*[@id=\"form-register\"]/div/div/button"));
        jse.executeScript("arguments[0].click()", regbtn);
        String confmsg = driver.findElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")).getText();
        System.out.println("Success Message : "+confmsg);

        Assert.assertEquals(confmsg, "Your Account Has Been Created!");

        driver.close();

    }
}
