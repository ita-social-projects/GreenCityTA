package com.ita.edu.greencity.ui.pages.sign_in;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPassword {
    @FindBy(how = How.CSS, using = "a.close-modal-window")
    private WebElement close;

    @FindBy(how = How.CSS, using = "input.ng-pristine.ng-invalid.alert-email-validation.ng-touched")
    private WebElement email;

    @FindBy(how = How.CSS, using = "button.ubs-primary-global-button")
    private WebElement submit;

    @FindBy(how = How.CSS, using = "button.google-sign-in")
    private WebElement signInGoogle;

    @FindBy(how = How.CSS, using = "a.ubs-sign-in-link")
    private WebElement backToSignIn;



    public void clickClose() {
        close.click();
    }

    public void inputEmail(String emailInput) {
        email.sendKeys(emailInput);
    }

    public void clickSubmit() {
        submit.click();
    }

    public void clickSignInGoogle() {
        signInGoogle.click();
    }

    public void clickBackToSignIn() {
        backToSignIn.click();
    }
}
