package com.ita.edu.greencity.ui.pages.header;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignUpComponent {
    @FindBy(how = How.XPATH,using = "//@class='cross-btn'")
    private WebElement exitButton;

    @FindBy(how = How.XPATH,using = "//*[contains(@class, 'ng-pristine')]/input[@id='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id='firstName']")
    private WebElement userNameField;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'password']//*[@id='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname = 'repeatPassword']//*[@id='password']")
    private WebElement confirmPasswordField;

    @FindBy(how = How.XPATH, using = "//button[@class='ubs-primary-global-button']")
    private WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//button[@class='google-sign-in']")
    private WebElement signUpWithGoogleButton;

    @FindBy(how = How.XPATH, using = "//a[@class='ubs-exist-sign-in']")
    private WebElement signInLinkButton;

    public SignUpComponent clickOnExitButton () {
        exitButton.click();
        return this;
    }

    public SignUpComponent inputEmailIntoField (String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignUpComponent inputUserNameIntoField (String userName) {
        userNameField.sendKeys(userName);
        return this;
    }

    public SignUpComponent inputPasswordIntoField (String password) {
        passwordField.sendKeys(password);
        return  this;
    }

    public SignUpComponent inputConfirmPasswordIntoField (String password) {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public SignUpComponent clickOnSignUpButton () {
        signUpButton.click();
        return this;
    }

    public void clickOnSignUpWithGoogleButton () {
        signUpWithGoogleButton.click();
    }

    public void clickOnSignInLinkButton () {
        signInLinkButton.click();
    }
}
