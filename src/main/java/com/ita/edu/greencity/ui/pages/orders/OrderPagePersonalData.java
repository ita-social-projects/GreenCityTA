package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class OrderPagePersonalData extends BasePage {

    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-data-title']")
    private WebElement personalDataTitle;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'firstName']")
    private WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'lastName']")
    private WebElement lastNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'phoneNumber']")
    private WebElement phoneNumberField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@id= 'flexCheckDefault']")
    private WebElement anotherClientButton;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientFirstName']")
    private WebElement anotherClientFirstNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientLastName']")
    private WebElement anotherClientLastNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientPhoneNumber']")
    private WebElement anotherClientPhoneNumberField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'anotherClientEmail']")
    private WebElement anotherClientEmailField;

    @FindBy(how = How.XPATH, using = "//h2[@class ='address-title']")
    private WebElement addressTitle;

    @FindBy(how = How.XPATH, using = "//button[@class ='btn add-address']")
    private WebElement addAddressButton;

    @FindBy(how = How.XPATH, using = "//h5[@class ='comment-title']")
    private WebElement commentTitle;

    @FindBy(how = How.XPATH, using = "//textarea[@formcontrolname= 'addressComment']")
    private WebElement addressCommentField;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn back-btn m-0']")
    private WebElement backButton;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn cansel-button']")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button btn m-0']")
    private WebElement nextButton;

    public OrderPagePersonalData(WebDriver driver) {
        super(driver);
    }

    public OrderPageConfirmation clickOnNextButton() {
        nextButton.click();
        return new OrderPageConfirmation(driver);
    }

    public UbsHomePage clickOnCancelButton() {
        cancelButton.click();
        return new UbsHomePage(driver);
    }

    public OrderDetailsPage clickOnBackButton() {
        backButton.click();
        return new OrderDetailsPage(driver);
    }

    public OrderPagePersonalData enterAddressComment(final String addressComment) {
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment, Keys.ENTER);
        return this;
    }

    public String getTextFromCommentTitle() {
        return commentTitle.getText();
    }

    public AddNewAddress clickOnAddAddressButton() {
//        waitUntilElementToBeClickable(By.xpath("//button[@class ='btn add-address']"),10);
        addAddressButton.click();
        return new AddNewAddress(driver);
    }

    public String getTextFromAddressTitle() {
        return addressTitle.getText();
    }

    public OrderPagePersonalData enterAnotherClientEmail(final String email) {
        anotherClientEmailField.clear();
        anotherClientEmailField.sendKeys(email, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterAnotherClientPhoneNumber(final String phoneNumber) {
        anotherClientPhoneNumberField.clear();
        anotherClientPhoneNumberField.sendKeys(phoneNumber, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterAnotherClientLastName(final String lastName) {
        anotherClientLastNameField.clear();
        anotherClientLastNameField.sendKeys(lastName, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterAnotherClientFirstName(final String firstName) {
        anotherClientFirstNameField.clear();
        anotherClientFirstNameField.sendKeys(firstName, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData clickOnAnotherClientButton() {
        anotherClientButton.click();
        return this;
    }

    public OrderPagePersonalData enterEmail(final String email) {
        emailField.clear();
        emailField.sendKeys(email, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterPhoneNumber(final String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterLastName(final String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterFirstName(final String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName, Keys.ENTER);
        return this;
    }

    public String getTextFromPersonalDataTitle() {
        return personalDataTitle.getText();
    }
}