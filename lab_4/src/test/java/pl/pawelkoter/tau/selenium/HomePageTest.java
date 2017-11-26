package pl.pawelkoter.tau.selenium;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import pl.pawelkoter.tau.selenium.pages.HomePage;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest {

    private static WebDriver _driver;
    private HomePage _homePage;

    @BeforeClass
    public static void driverSetup(){

        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("phantomjs.binary"));
//        capabilities.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\phantomjs.exe");
        _driver = new PhantomJSDriver(capabilities);

        _driver.manage()
               .timeouts()
               .implicitlyWait(5, TimeUnit.MICROSECONDS);
    }

    @AfterClass
    public static void quitDriver() throws Exception {
        _driver.quit();
    }

    @Before
    public void homePageSetup() {
        _homePage = new HomePage( _driver );
        _homePage.open();
    }

    @Ignore("Fix it later")
    @Test
    public void thereIsPhoneNumberInFooter() {
        WebElement phoneNumber = _homePage.findPhoneNumber();

        assertThat(phoneNumber).isNotNull();
    }

    @Test
    public void cartLinkIsDisplayed() {
        WebElement cart = _homePage.getCartLink();

        assertThat( cart.isDisplayed() ).isTrue();
    }

    @Test
    public void sliderIsDisplayed() {
        WebElement slider = _homePage.getSlider();

        assertThat( slider.isDisplayed() ).isTrue();
    }

    @Test
    public void bestSellersContainsSevenItems() {
        _homePage.clickBestSellersLink();

        int count = _homePage.getBestSellersCount();

        assertThat( count ).isEqualTo( 7 );
    }

    @Test
    public void dressIsOnBestSellersList() {
        _homePage.clickBestSellersLink();
        boolean isDressOnBestSellersList = _homePage.isDressOnBestSellersList();

        assertThat( isDressOnBestSellersList ).isTrue();
    }

    @Test
    public void bestSellersAreDisplayedAfterClickOnLink() {
        _homePage.clickBestSellersLink();

        assertThat( _homePage.areBestSellersVisible() ).isTrue();
    }

    @Ignore("Fix it later")
    @Test
    public void dressesLink() {
        _homePage.hoverOverDressesLink();
    }

    @Test
    public void goToLoginPage() {
        _homePage.clickLoginLink();

        String url = _homePage.getUrl();
        assertThat( url ).isEqualTo( HomePage.LOGIN_URL );
    }
}