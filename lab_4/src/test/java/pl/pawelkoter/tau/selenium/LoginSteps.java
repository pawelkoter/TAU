package pl.pawelkoter.tau.selenium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import pl.pawelkoter.tau.selenium.pages.LoginPage;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    private LoginPage _loginPage;

    @Given("^User is on login page$")
    @SuppressWarnings("Duplicates")
    public void user_is_on_login_page() throws Throwable {
        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled( true );
        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("phantomjs.binary"));
//        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\phantomjs.exe");
        WebDriver _driver = new PhantomJSDriver( capabilities );

        _driver.manage()
                .timeouts()
                .implicitlyWait(20, TimeUnit.MICROSECONDS);

        _driver.manage()
                .window()
                .setSize( new Dimension( 1920, 1050 ) );

        _loginPage = new LoginPage( _driver );
        _loginPage.open();
    }

    @Given("^Uses (.*) and (.*)$")
    public void uses_this_is_email_and_password(String email, String password) throws Throwable {
        _loginPage.setLoginEmail( email );
        _loginPage.setLoginPassword( password );
    }

    @When("^He clicks submit button$")
    public void he_clicks_submit_button() throws Throwable {
        _loginPage.clickLoginSubmitButton();
    }

    @Then("^He is redirected to user account page$")
    public void he_is_redirected_to_user_account_page() throws Throwable {
        _loginPage.takeScreenshot( "bdd-login-success" );
        assertThat( _loginPage.getUrl() ).isEqualTo( LoginPage.MY_ACCOUNT_URL );
    }


    @Then("^Authentication error is displayed$")
    public void authentication_error_is_displayed() throws Throwable {
        _loginPage.takeScreenshot( "bdd-login-fail" );
        assertThat( _loginPage.isAuthenticationErrorDisplayed() ).isTrue();
    }

}