package com.ita.edu.greencity.ui.pages.sign_in;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignIn {
    private WebDriver driver;

    public SignIn(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(how = How.CSS, using = "a.close-modal-window")
    private WebElement close;

    @FindBy(how = How.CSS, using = "input.successful-email-validation.ng-untouched.ng-pristine.ng-invalid")
    private WebElement email;

    @FindBy(how = How.CSS, using = "input[id = password]")
    private WebElement password;

    @FindBy(how = How.CSS, using = "span.show-hide-btn")
    private WebElement showHidePassword;

    @FindBy(how = How.CSS, using = "a.ubs-forgot-password")
    private WebElement forgotPassword;

    @FindBy(how = How.CSS, using = "button.ubs-primary-global-button")
    private WebElement signIn;

    @FindBy(how = How.CSS, using = "button.google-sign-in")
    private WebElement signInGoogle;

    @FindBy(how = How.CSS, using = "a.ubs-sign-up-link")
    private WebElement signUp;



    public void clickCloseBtn() {
        close.click();
    }

    public void inputEmail(String emailInput) {
        email.sendKeys(emailInput);
    }

    public void inputPassword(String passwordInput) {
        password.sendKeys(passwordInput);
    }

    public void clickShowHidePassword() {
        showHidePassword.click();
    }

    public void clickForgotPassword() {
        forgotPassword.click();
    }

    public void clickSignIn() {
        signIn.click();
    }

    public void clickSignInGoogle() {
        signInGoogle.click();
    }

    public void clickSignUp() {
        signUp.click();
    }
}
