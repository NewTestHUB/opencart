import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.Duration;
import java.util.ResourceBundle;

public class Opencart_Login {

    public static ResourceBundle rb;

    public static WebDriver driver;
    public static void main(String [] args) {

        rb = ResourceBundle.getBundle("config");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(rb.getString("appURL"));

        driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[2]/a")).click();

        driver.findElement(By.name("email")).sendKeys(rb.getString("email"));
        driver.findElement(By.name("password")).sendKeys(rb.getString("password"));
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Boolean cnfmsg = driver.findElement(By.xpath("//h2[text()='My Account']")).isDisplayed();
        WebElement msg = driver.findElement(By.xpath("//h2[text()='My Account']"));
        System.out.println(cnfmsg);
        System.out.println(msg.getText());

        try {

            Assert.assertTrue(cnfmsg,"Invalid data");
        } catch (Exception e) {
            System.out.println("Invalid Data");
            e.getMessage();
        }


        WebElement logout = driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()",logout);

        driver.close();

    }
}
