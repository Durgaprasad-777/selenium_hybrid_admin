package base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;


public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup(){
        String browser = ConfigReader.get("browser");
        logger.info("Launching browser : {}",browser);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            if (ConfigReader.getBoolean("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments("--start-maximized");
            }

            driver = new ChromeDriver(options);



        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        String baseUrl = ConfigReader.get("baseurl");
        logger.info("Opening URL : {}",baseUrl);
        driver.get(ConfigReader.get("baseurl"));
    }

    @AfterMethod
    public void tearMethod(){
        if(driver != null){
            driver.quit();
        }


    }
    public WebDriver getDriver() {
        return driver;
    }

}








