package pages;

import base.BasePage;
import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private WebDriver driver;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    //private By flashMessage = By.id("flash");

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        super(driver);
    }
    public void enterUsername(String username) {
        logger.info("Entering username");
        type(usernameInput,username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        logger.info("Clicking login button");
        click(loginButton);
    }

    public void loginWithValidCredentials(String username, String password) {
        logger.info("Logging in with valid credentials");
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

    }

    public LoginPage loginWithInvalidCredentials(String username, String password) {
        logger.info("Logging in with invalid credentials");
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        return this;
    }

}
