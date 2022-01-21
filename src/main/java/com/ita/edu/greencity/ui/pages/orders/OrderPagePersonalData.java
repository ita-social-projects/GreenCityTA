package com.ita.edu.greencity.ui.pages.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderPagePersonalData {

    protected WebDriver driver;

    public OrderPagePersonalData(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

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




    public void clickOnNextButton(){
        nextButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void clickOnBackButton(){
        backButton.click();
    }

    public void enterAddressComment(final String addressComment){
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment);
    }

    public String getTextFromCommentTitle(){
        return commentTitle.getText();
    }

    public void clickOnAddAddressButton(){
        addAddressButton.click();
    }

    public String getTextFromAddressTitle(){
        return addressTitle.getText();
    }

    public void enterAnotherClientEmail(final String email){
        anotherClientEmailField.clear();
        anotherClientEmailField.sendKeys(email);
    }

    public void enterAnotherClientPhoneNumber(final String phoneNumber){
        anotherClientPhoneNumberField.clear();
        anotherClientPhoneNumberField.sendKeys(phoneNumber);
    }

    public void enterAnotherClientLastName(final String lastName){
        anotherClientLastNameField.clear();
        anotherClientLastNameField.sendKeys(lastName);
    }

    public void enterAnotherClientFirstName(final String firstName){
        anotherClientFirstNameField.clear();
        anotherClientFirstNameField.sendKeys(firstName);
    }

    public void clickOnAnotherClientButton(){
        anotherClientButton.click();
    }

    public void enterEmail(final String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPhoneNumber(final String phoneNumber){
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterLastName(final String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterFirstName(final String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public String getTextFromPersonalDataTitle(){
        return personalDataTitle.getText();
    }
}
