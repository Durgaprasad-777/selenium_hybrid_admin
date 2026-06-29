package tests;

import base.BaseTest;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
//import pages.SecureAreaPage;
import utils.ConfigReader;
import utils.ExcelUtils;

import java.time.Duration;


public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return ExcelUtils.getExcelData(
                "C:/Users/2492331/Downloads/seleniumproject1/src/test/resources//LoginData.xlsx",
                "Sheet1"
        );
    }

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(dataProvider = "loginData")
    public void validLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithValidCredentials(username, password);

        logger.info("Validating success message");

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getInt("timeout"))
        );
        wait.until(ExpectedConditions.urlToBe("https://cinebookfrontend.netlify.app/movies"));

        Assert.assertEquals(driver.getCurrentUrl(),"https://cinebookfrontend.netlify.app/movies");

    }

    @Test(dataProvider = "loginData")
    public void invalidPasswordTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);

       loginPage.loginWithInvalidCredentials(username, password);
        logger.info("Validating invalid password message");
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getInt("timeout"))
        );
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "User should remain on login page after invalid login. Actual URL: " + driver.getCurrentUrl()
        );
    }
}
