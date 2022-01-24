package com.ita.edu.greencity.ui.pages.sign_in;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.SignUpComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BasePage {

    public SignInComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//a[@class = \"close-modal-window\"]")
    private WebElement close;

    @FindBy(how = How.XPATH, using = "//input[@id = \"email\"]")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//input[@id = \"password\"]")
    private WebElement password;

    @FindBy(how = How.XPATH, using = "//span[@class = \"show-hide-btn\"]")
    private WebElement showHidePassword;

    @FindBy(how = How.XPATH, using = "//div[@class = \"forgot-wrapper\"]/a[@class = \"ubs-forgot-password\"]")
    private WebElement forgotPassword;

    @FindBy(how = How.XPATH, using = "//app-submit-button/button[@class = \"ubs-primary-global-button\"]")
    private WebElement signIn;

    @FindBy(how = How.XPATH, using = "//app-google-btn/button[@class = \"google-sign-in\"]")
    private WebElement signInGoogle;

    @FindBy(how = How.XPATH, using = "//div[@class = \"missing-account\"]/p/a[@class = \"ubs-sign-up-link\"]")
    private WebElement signUp;

    @FindBy(how = How.XPATH, using = "//div[@id = \"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;

    @FindBy(how = How.XPATH, using = "//div[@id = \"pass-err-msg\"]/app-error/div")
    private WebElement errorPassword;

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

    public SelectRegion clickSignIn() {
        signIn.click();

        return new SelectRegion(driver);
    }

    public SelectRegion clickSignInGoogle() {
        signInGoogle.click();

        return new SelectRegion(driver);
    }

    public SignUpComponent clickSignUp() {
        signUp.click();

        return new SignUpComponent(driver);
    }

    public String getErrorEmailMessage() {
        return errorEmail.getText();
    }

    public String getErrorPasswordMessage() {
        return errorPassword.getText();
    }
}
