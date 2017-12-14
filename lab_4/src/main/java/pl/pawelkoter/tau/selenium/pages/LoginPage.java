package pl.pawelkoter.tau.selenium.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class LoginPage {
    public static final String CREATE_ACCOUNT_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
    public static final String MY_ACCOUNT_URL = "http://automationpractice.com/index.php?controller=my-account";
    public static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    private WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement createAccountEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement submitButton;

    @FindBy(id = "create_account_error")
    private WebElement createAccountErrorMessage;

    @FindBy(id = "email")
    private WebElement loginEmailInput;

    @FindBy(id = "passwd")
    private WebElement loginPasswordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement loginSubmitButton;

    @FindBy(linkText = "Sign out")
    private WebElement signOutButton;

    @FindBy(className = "alert")
    private WebElement authenticationErrorMessage;

    public LoginPage( WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get( "http://automationpractice.com/index.php?controller=authentication" );
    }

    public void setEmail(String email) {
        createAccountEmailInput.sendKeys( email );
    }

    public void clickSubmitButton() {
        Actions submitActions = new Actions(driver);

        submitActions.click(submitButton).pause(5000).build().perform();
    }

    public boolean isErrorMessageShown() {
        File scrFile = ((TakesScreenshot )driver).getScreenshotAs( OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\target\\selenium-screenshots\\createAccountErrorMessage.png"));
        } catch ( IOException e ) {
            e.getCause();
        }
        return createAccountErrorMessage.isDisplayed();
    }

    public RegisterPage goToRegisterPage() {
        clickSubmitButton();
        return new RegisterPage( driver );
    }

    public void setLoginEmail(String email) {
        loginEmailInput.sendKeys( email );
    }

    public void setLoginPassword(String password) {
        loginPasswordInput.sendKeys( password );
    }

    public void clickLoginSubmitButton() {
        loginSubmitButton.click();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    public boolean isAuthenticationErrorDisplayed() {
        return authenticationErrorMessage.isDisplayed();
    }

    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot )driver).getScreenshotAs( OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\target\\selenium-screenshots\\" + fileName + ".png"));
        } catch ( IOException e ) {
        }
    }
}
