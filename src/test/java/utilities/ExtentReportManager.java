package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter; // UI of the report
    public ExtentReports extent; // populate common info on the report
    public ExtentTest test; // creating test case entries in the report and update status of the test methods
    String repName;

    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        repName = "Test-Report-"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName); //specify location of the report

        sparkReporter.config().setDocumentTitle("opencart Automation Report");// Title of report
        sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application","opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

    }

    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName()); // create a new entry in the report
        test.log(Status.PASS,"Test Case Passed is: "+result.getName()); //Update status p/f/s
    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Case FAILED is: "+result.getName());
        test.log(Status.FAIL, "Test Case FAILED Cause is: "+result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Case SKIPPED is: "+result.getName());
        test.log(Status.SKIP, "Test Case Skip cause is : "+result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {

        extent.flush();
    }

}
