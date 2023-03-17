package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.BasePage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {
    public static WebDriver driver;

    public Logger logger;
    public ResourceBundle rb;

    @BeforeClass(groups = {"Master","Regression","Sanity"})
    @Parameters("browser")
    public void setup(String br) {

        logger = LogManager.getLogger(this.getClass());
        rb = ResourceBundle.getBundle("config");

//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});

        if(br.equals("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if(br.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(rb.getString("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(groups = {"Master","Regression","Sanity"})
    public void tearDown() {

        driver.close();

    }

    public String randomString() {

        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber() {

        String generatedNumber = RandomStringUtils.randomNumeric(5);
        return generatedNumber;
    }

    public String randomAlphaNumeric() {

        String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(10);
        return generatedAlphaNumeric;
    }

 public String captureScreen(String tname) {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

     TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
     File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
     String destination = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".jpg";
//     String destination = "./screenshots"+"_"+timeStamp+".jpg";
     try {
         FileUtils.copyFile(source,new File(destination));
     } catch (Exception e) {
         e.getMessage();
     }
             return destination;
 }

}
