package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance(){
        if(extent == null){
            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String reportPath = "reports/AutomaticReport_"+timestamp+".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setReportName("Selenium Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "Heroku Selenium Practice");
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.get("baseurl"));
            extent.setSystemInfo("Headless", ConfigReader.get("headless"));
        }
        return extent;
    }
}
