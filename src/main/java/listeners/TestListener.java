package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent = ExtentReportManager.getReportInstance();

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}", result.getName());

        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);

        test.get().info("Test started: "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        logger.error("Failure reason: {}", result.getThrowable().getMessage());

        test.get().fail("Test failed");
        test.get().fail(result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();



        if (driver != null) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            logger.info("Screenshot captured: {}", screenshotPath);
        } else {
            logger.warn("Driver is null. Screenshot was not captured.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());
        test.get().skip("Test skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Flushing Extent report");

        extent.flush();

        test.remove();
    }
}
