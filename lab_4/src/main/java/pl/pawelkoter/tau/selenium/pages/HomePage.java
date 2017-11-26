package pl.pawelkoter.tau.selenium.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomePage {
    public static String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private WebDriver driver;

    @FindBy(partialLinkText = "Cart")
    private WebElement cartLink;

    @FindBy(id = "homepage-slider")
    private WebElement slider;

    @FindBy(linkText = "BEST SELLERS")
    private WebElement bestSellersLink;

    @FindBy(xpath = "//a[contains(text(),'Dresses')])")
    private WebElement dressesLink;

    @FindBy(linkText = "Sign in")
    private WebElement loginLink;

    public HomePage( WebDriver driver ) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://automationpractice.com");
        PageFactory.initElements(driver, this);
    }

    public WebElement findPhoneNumber() {
        WebElement element = driver.findElement( By.xpath( "//section[@id='block_contact_infos']//*[text(),'Call us now']" ) );

        return element;
    }

    public WebElement getCartLink() {
        return cartLink;
    }

    public WebElement getSlider() {
        return slider;
    }

    public void clickBestSellersLink() {
        bestSellersLink.click();
    }

    public int getBestSellersCount() {
        List< WebElement > bestSellers = driver.findElements( By.xpath( "//ul[@id='blockbestsellers']/li" ) );

        return bestSellers.size();
    }

    public boolean isDressOnBestSellersList() {
        WebElement bestSellers = driver.findElement( By.xpath( "//ul[@id='blockbestsellers']" ) );

        WebElement dressLink = bestSellers.findElement( By.linkText("Printed Chiffon Dress") );
        return dressLink != null;
    }

    public boolean areBestSellersVisible() {
        List< WebElement > bestSellers = driver.findElements( By.xpath( "//ul[@id='blockbestsellers']//div[@class='product-container']" ) );

        for ( WebElement element : bestSellers) {
            if(!element.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public void hoverOverDressesLink() {
        Actions action = new Actions(driver);

        action.moveToElement(dressesLink).build().perform();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs( OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\dressesMenu.png"));
        } catch ( IOException e ) {

        }
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}