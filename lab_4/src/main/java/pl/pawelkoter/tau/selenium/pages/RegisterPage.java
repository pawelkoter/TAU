package pl.pawelkoter.tau.selenium.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class RegisterPage {
    private WebDriver driver;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;
    @FindBy(id = "customer_lastname")
    private WebElement lastnameInput;
    @FindBy(id = "firstname")
    private WebElement firstNameAddressInput;
    @FindBy(id = "lastname")
    private WebElement lastnameAddressInput;
    @FindBy(id = "passwd")
    private WebElement passwordInput;
    @FindBy(id = "address1")
    private WebElement address1Input;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "id_state")
    private WebElement stateSelect;
    @FindBy(id = "postcode")
    private WebElement postCodeInput;
    @FindBy(id = "phone")
    private WebElement phoneInput;
    @FindBy(id = "submitAccount")
    private WebElement submitButton;

    public RegisterPage( WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RegisterPage setFirstName(String firstName) {
        firstNameInput.sendKeys( firstName );
        return this;
    }

    public RegisterPage setLastName(String lastName) {
        lastnameInput.sendKeys( lastName );
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.sendKeys( password );
        return this;
    }

    public RegisterPage setAddress1(String address) {
        address1Input.sendKeys( address );
        return this;
    }

    public RegisterPage setCity(String city) {
        cityInput.sendKeys( city );
        return this;
    }

    public RegisterPage selectState(String state) {
        new Select( stateSelect ).selectByVisibleText( state );
        return this;
    }

    public RegisterPage setPostCode(String postCode) {
        postCodeInput.sendKeys( postCode );
        return this;
    }

    public RegisterPage setPhoneNumber(String phoneNumber) {
        phoneInput.sendKeys( phoneNumber );
        return this;
    }

    public RegisterPage setFirstNameAddress(String firstName) {
        firstNameAddressInput.sendKeys( firstName );
        return this;
    }

    public RegisterPage setLastNameAddress(String lastName) {
        lastnameAddressInput.sendKeys( lastName );
        return this;
    }

    public RegisterPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot )driver).getScreenshotAs( OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(".\\" + fileName + ".png"));
        } catch ( IOException e ) {
        }
    }

}