package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
//import pages.SecureAreaPage;
import utils.ExcelUtils;


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

//        loginPage.loginWithValidCredentials(username, password);
//
//        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);
//
//        String message = secureAreaPage.getFlashMessage();

        logger.info("Validating success message");

        Assert.assertEquals(driver.getCurrentUrl(),"https://cine-book-one-rosy.vercel.app/movies");

//        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test(dataProvider = "loginData")
    public void invalidPasswordTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);

       loginPage.loginWithInvalidCredentials(username, password);
//
//        String message = loginPage.getFlashMessage();

        logger.info("Validating invalid password message");

//        Assert.assertTrue(message.contains(expectedMessage));

        Assert.assertNotEquals(driver.getCurrentUrl(),"https://cine-book-one-rosy.vercel.app/login");
    }
}
