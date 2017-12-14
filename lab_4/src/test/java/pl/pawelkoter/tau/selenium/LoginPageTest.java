package pl.pawelkoter.tau.selenium;

import org.junit.*;
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

public class LoginPageTest {
    private static WebDriver _driver;
    private LoginPage _loginPage;

    @BeforeClass
    public static void driverSetup(){

        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled( true );
        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("phantomjs.binary"));
//        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\phantomjs.exe");
        _driver = new PhantomJSDriver(capabilities);

        _driver.manage()
                .timeouts()
                .implicitlyWait(20, TimeUnit.MICROSECONDS);

        _driver.manage()
                .window()
                .setSize( new Dimension( 1920, 1050 ) );
    }

    @Before
    public void homePageSetup() {
        _loginPage = new LoginPage( _driver );
        _loginPage.open();
    }

    @AfterClass
    public static void quitPage() throws Exception {
        _driver.quit();
    }

    @Test
    public void registrationFailure() {
        _loginPage.setEmail( "definitely#not@an,email" );
        _loginPage.clickSubmitButton();
        assertThat(_loginPage.isErrorMessageShown()).isTrue();
    }

    @Test
    public void registrationSuccess() {
        String email = UUID.randomUUID().toString();
        _loginPage.setEmail( email + "@email.org" );
        RegisterPage registerPage = _loginPage.goToRegisterPage();

        registerPage.takeScreenshot( "go_to_create_account_page" );

        registerPage.setFirstName( "Jaś" )
                .setLastName( "Fasola" )
                .setPassword( "password" )
                .setFirstNameAddress( "Jaś" )
                .setLastNameAddress( "Fasola" )
                .setAddress1( "Błotna" )
                .setCity( "Wąchock" )
                .selectState( "Alabama" )
                .setPostCode( "12345" )
                .setPhoneNumber( "123456789" );

        registerPage.clickSubmitButton();
        registerPage.takeScreenshot( "registred" );

        assertThat( _loginPage.getUrl() ).isEqualTo( LoginPage.MY_ACCOUNT_URL );
    }

    @Test
    public void loginLogoutTest() {
        _loginPage.setLoginEmail( "this@is.email" );
        _loginPage.setLoginPassword( "password" );
        _loginPage.clickLoginSubmitButton();

        assertThat( _loginPage.getUrl() ).isEqualTo( LoginPage.MY_ACCOUNT_URL );

        _loginPage.clickSignOutButton();

        assertThat( _loginPage.getUrl() ).isEqualTo( LoginPage.LOGIN_URL );
    }

    @Test
    public void loginWithIncorrectCredentials() {
        _loginPage.setLoginEmail( "this@is.email" );
        _loginPage.setLoginPassword( "haslo" );
        _loginPage.clickLoginSubmitButton();

        assertThat( _loginPage.isAuthenticationErrorDisplayed() ).isTrue();
    }
}
