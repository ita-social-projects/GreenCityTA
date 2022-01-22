package com.ita.edu.greencity.ui.pages.sign_in;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.SignUpComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BasePage {

    public SignInComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class = \"wrapper\"]//a[@class = \"close-modal-window\"]")
    private WebElement close;

    @FindBy(how = How.XPATH, using = "//app-email-input-field//input[@id = \"email\"]")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//form[@class = \"ng-pristine ng-invalid ng-touched\"]//input[@id = \"password\"]")
    private WebElement password;

    @FindBy(how = How.XPATH, using = "//form[@class = \"ng-pristine ng-invalid ng-touched\"]//span[@role = \"button\"][@class = \"show-hide-btn\"]")
    private WebElement showHidePassword;

    @FindBy(how = How.XPATH, using = "//div[@class = \"forgot-wrapper\"]//a[@class = \"ubs-forgot-password\"]")
    private WebElement forgotPassword;

    @FindBy(how = How.XPATH, using = "//app-submit-button//button[@class = \"ubs-primary-global-button\"]")
    private WebElement signIn;

    @FindBy(how = How.XPATH, using = "//app-google-btn//button[@class = \"google-sign-in\"]")
    private WebElement signInGoogle;

    @FindBy(how = How.XPATH, using = "//div[@class = \"missing-account\"]//a[@class = \"ubs-sign-up-link\"]")
    private WebElement signUp;



    public UbsHomePage clickCloseBtn() {
        close.click();

        return new UbsHomePage(driver);
    }

    public SignInComponent inputEmail(String emailInput) {
        email.sendKeys(emailInput);

        return this;
    }

    public SignInComponent inputPassword(String passwordInput) {
        password.sendKeys(passwordInput);

        return this;
    }

    public SignInComponent clickShowHidePassword() {
        showHidePassword.click();

        return this;
    }

    public ForgotPasswordComponent clickForgotPassword() {
        forgotPassword.click();

        return new ForgotPasswordComponent(driver);
    }

    // ???
    public void clickSignIn() {
        signIn.click();
    }

    // ???
    public void clickSignInGoogle() {
        signInGoogle.click();
    }

    public SignUpComponent clickSignUp() {
        signUp.click();

        return new SignUpComponent(driver);
    }
}
