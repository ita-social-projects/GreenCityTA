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

    @FindBy(how = How.XPATH, using = "//h1[@class ='h1 text-center form-title']")
    private WebElement UBSTitle;


    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'firstName']")
    private WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname= 'lastName']")
    private WebElement surnameField;

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

    @FindBy(how = How.XPATH, using = "//img[@alt = 'edit image']")
    private WebElement editCollectionAddressButton;

    @FindBy(how = How.XPATH, using = "//img[@alt = 'delete image']")
    private WebElement deleteCollectionAddressButton;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[2]/div/app-ubs-input-error/div")
    private WebElement nameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[4]/div/app-ubs-input-error/div")
    private WebElement surnameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[3]/div/app-ubs-input-error/div")
    private WebElement phoneNumberErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[5]/div/app-ubs-input-error/div")
    private WebElement emailErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[2]/div/app-ubs-input-error/div")
    private WebElement anotherClientNameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[3]/div/app-ubs-input-error/div")
    private WebElement anotherClientSurnameErrorMessage;

    @FindBy(how = How.XPATH, using = "//form/div[1]/div[4]/div/app-ubs-input-error/div")
    private WebElement anotherClientPhoneNumberErrorMessage;


    public String getTextFromAnotherClientPhoneNumberErrorMessage() {
        return anotherClientPhoneNumberErrorMessage.getText();
    }

    public String getTextFromAnotherClientSurnameErrorMessage() {
        return anotherClientSurnameErrorMessage.getText();
    }

    public String getTextFromAnotherClientNameErrorMessage() {
        return anotherClientNameErrorMessage.getText();
    }

    public String getTextFromEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getTextFromPhoneNumberErrorMessage() {
        return phoneNumberErrorMessage.getText();
    }

    public String getTextFromSurnameErrorMessage() {
        return surnameErrorMessage.getText();
    }

    public String getTextFromNameErrorMessage() {
        return nameErrorMessage.getText();
    }


    public OrderPagePersonalData clickOnDeleteCollectionAddressButton() {
        deleteCollectionAddressButton.click();
        return this;
    }

    public AddNewAddress clickOnEditAddressButton() {
        editCollectionAddressButton.click();
        return new AddNewAddress(driver);
    }

    public OrderPageConfirmation clickOnNextButton() {
        sleep(5000);
        nextButton.click();
        return new OrderPageConfirmation(driver);
    }

    public UbsHomePage clickOnCancelButton() {
        cancelButton.click();
        return new UbsHomePage(driver);
    }

    public OrderDetailsPage clickOnBackButton() {
        sleep(2000);
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
        this.sleep(2000);
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

    public OrderPagePersonalData entersurname(final String lastName) {
        surnameField.clear();
        surnameField.sendKeys(lastName, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData enterFirstName(final String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName, Keys.ENTER);
        return this;
    }

    public OrderPagePersonalData clickForGetMessage(){
        UBSTitle.click();
        return this;
    }

    public String getTextFromPersonalDataTitle() {
        return personalDataTitle.getText();
    }

    public OrderPagePersonalData(WebDriver driver) {
        super(driver);
    }
}