package com.ita.edu.greencity.ui.pages.sign_in;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ForgotPasswordComponent extends BasePage {

    public ForgotPasswordComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class = \"wrapper\"]//a[@class = \"close-modal-window\"")
    private WebElement close;

    @FindBy(how = How.XPATH, using = "//div[@class = \"restore-password-container\"]//input[@id = \"email\"]")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//form[@class = \"restore-password-form ng-untouched ng-pristine ng-invalid\"]//button[@type = \"submit\"]")
    private WebElement submit;

    @FindBy(how = How.XPATH, using = "//app-google-btn[@class = \"google-sing-in-button\"]/button[@class = \"google-sign-in\"]")
    private WebElement signInGoogle;

    @FindBy(how = How.XPATH, using = "//div[@class = \"mentioned-password\"]/a[@class = \"ubs-sign-in-link\"]")
    private WebElement backToSignIn;



    public UbsHomePage clickClose() {
        close.click();

        return new UbsHomePage(driver);
    }

    public ForgotPasswordComponent inputEmail(String emailInput) {
        email.sendKeys(emailInput);

        return this;
    }

    public UbsHomePage clickSubmit() {
        submit.click();

        return new UbsHomePage(driver);
    }

    // ???
    public void clickSignInGoogle() {
        signInGoogle.click();
    }

    public SignInComponent clickBackToSignIn() {
        backToSignIn.click();

        return new SignInComponent(driver);
    }
}