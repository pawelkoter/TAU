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
import pl.pawelkoter.tau.selenium.pages.RegisterPage;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterSteps {
    private LoginPage _loginPage;
    private RegisterPage _registerPage;

    @Given("^A login page$")
    @SuppressWarnings("Duplicates")
    public void a_login_page() throws Throwable {
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

    @Given("^User enters valid email$")
    public void user_enters_valid_email() throws Throwable {
        String email = UUID.randomUUID().toString();
        _loginPage.setEmail( email + "@email.org" );
    }

    @Given("^Clicks create account button$")
    public void clicks_create_account_button() throws Throwable {
        _registerPage = _loginPage.goToRegisterPage();
    }

    @Given("^Enters first name (.*)")
    public void enters_first_name(String firstName) throws Throwable {
        _registerPage.setFirstName( firstName );
    }

    @Given("^Enters last name (.*)$")
    public void enters_last_name(String lastName) throws Throwable {
        _registerPage.setLastName( lastName );
    }

    @Given("^Enters password (.*)$")
    public void enters_password(String password) throws Throwable {
        _registerPage.setPassword( password );
    }

    @Given("^Enters address first name (.*)$")
    public void enters_address_first_name(String addressFirstName) throws Throwable {
        _registerPage.setFirstNameAddress( addressFirstName );
    }

    @Given("^Enters address last name (.*)$")
    public void enters_address_last_name(String addressLastName) throws Throwable {
        _registerPage.setLastNameAddress( addressLastName );
    }

    @Given("^Enters street (.*)$")
    public void enters_street(String street) throws Throwable {
        _registerPage.setAddress1( street );
    }

    @Given("^Enters city (.*)$")
    public void enters_city(String city) throws Throwable {
        _registerPage.setCity( city );
    }

    @Given("^Selects state (.*)$")
    public void selects_state(String state) throws Throwable {
        _registerPage.selectState( state );
    }

    @Given("^Enters postal code (.*)$")
    public void enters_postal_code(String postalCode) throws Throwable {
        _registerPage.setPostCode( postalCode );
    }

    @Given("^Enters phone number (.*)$")
    public void enters_phone_namber(String phoneNumber) throws Throwable {
        _registerPage.setPhoneNumber( phoneNumber );
    }

    @When("^Finally he clicks register button$")
    public void finally_he_clicks_register_button() throws Throwable {
        _registerPage.clickSubmitButton();
    }

    @Then("^User account page shows up$")
    public void user_account_page_shows_up() throws Throwable {
        _loginPage.takeScreenshot( "bdd-register-success" );
        assertThat( _loginPage.getUrl() ).isEqualTo( LoginPage.MY_ACCOUNT_URL );
    }
}