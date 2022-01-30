package com.ita.edu.greencity.ui.pages.sign_up;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpComponent extends BasePage {
    @FindBy(how = How.XPATH, using = "//img[@class='cross-btn']")
    private WebElement exitButton;

    @FindBy(how = How.XPATH, using = "//div[@class='wrapper']//input[@id='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id='email-err-msg']/app-error/div")
    private WebElement blankEmailFieldAlert;

    @FindBy(how = How.XPATH, using = "//input[@id='firstName']")
    private WebElement userNameField;

    @FindBy(how = How.XPATH, using = "//*[@id='firstname-err-msg']/app-error/div")
    private WebElement blankUserNameFieldAlert;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'password']//input[@id='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'password']//app-error/div")
    private WebElement blankPasswordFieldAlert;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'password']//img[@class='image-show-hide-password']")
    private WebElement showHidePasswordButton;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'repeatPassword']//input[@id='password']")
    private WebElement confirmPasswordField;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'repeatPassword']//app-error/div")
    private WebElement blankConfirmPasswordFieldAlert;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'repeatPassword']//img[@class='image-show-hide-password']")
    private WebElement showHideConfirmPasswordButton;

    @FindBy(how = How.XPATH, using = "//button[@class='ubs-primary-global-button']")
    private WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//button[@class='google-sign-in']")
    private WebElement signUpWithGoogleButton;

    @FindBy(how = How.XPATH, using = "//a[@class='ubs-exist-sign-in']")
    private WebElement signInLinkButton;

    @FindBy(how = How.XPATH, using = "//div[@class='title']")
    private WebElement title;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'cdk-overlay-')]/snack-bar-container/simple-snack-bar/span")
    private WebElement successRegistrationAlert;

    public SignUpComponent(WebDriver driver) {
        super(driver);
    }

    public UbsHomePage clickOnExitButton() {
        exitButton.click();
        return new UbsHomePage(driver);
    }

    public SignUpComponent inputEmailIntoField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public String getTextOfBlankEmailFieldAlert() {
        return blankEmailFieldAlert.getText();
    }

    public SignUpComponent inputUserNameIntoField(String userName) {
        userNameField.sendKeys(userName);
        return this;
    }

    public String getTextOfBlankUserNameFieldAlert() {
        return blankUserNameFieldAlert.getText();
    }

    public SignUpComponent inputPasswordIntoField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public String getTextOfBlankPasswordFieldAlert() {
        return blankPasswordFieldAlert.getText();
    }

    public SignUpComponent clickOnShowHidePasswordButton() {
        showHidePasswordButton.click();
        return this;
    }

    public SignUpComponent inputConfirmPasswordIntoField(String password) {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public String getTextOfBlankConfirmPasswordFieldAlert() {
        return blankConfirmPasswordFieldAlert.getText();
    }

    public SignUpComponent clickOnShowHideConfirmPasswordButton() {
        showHideConfirmPasswordButton.click();
        return this;
    }

    public SignUpComponent clickOnSignUpButton() {
        signUpButton.click();
        return this;
    }

    public SignUpComponent clickOnSignUpWithGoogleButton() {
        signUpWithGoogleButton.click();
        return this;
    }

    public SignInComponent clickOnSignInLinkButton() {
        signInLinkButton.click();
        return new SignInComponent(driver);
    }

    public String getTextOfTitle() {
        return title.getText();
    }

    public boolean checkDisabledSignUpButton() {
        return (signUpButton.getAttribute("disabled")!= null);
    }

    public String getTextOfSuccessRegistrationAlert() {
        sleep(5000);
        return successRegistrationAlert.getText();
    }

    public boolean checkPasswordIsVisible() {
        return (passwordField.getAttribute("type").equals("text"));
    }

    public boolean checkConfirmPasswordIsVisible() {
        return (confirmPasswordField.getAttribute("type").equals("text"));
    }
}
