package com.ita.edu.greencity.ui.pages.ubs_homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginAndGoToUbs {
    @FindBy(how = How.CSS, using = "a.ubs-header-sign-in")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "input[id = email]")
    private WebElement email;

    @FindBy(how = How.CSS, using = "input[id = password]")
    private WebElement password;

    @FindBy(how = How.CSS, using = "button.ubs-primary-global-button")
    private WebElement buttonForSingIn;

    @FindBy(how = How.CSS, using = "button.btn")
    private WebElement nextButton;

//___________________________________________________

    public void pressLoginButton() {
        loginButton.click();
    }

    public void typeEmail(String emailInput) {
        email.sendKeys(emailInput);
    }

    public void typePassword(String passwordInput) {
        password.sendKeys(passwordInput);
    }

    public void pressButtonForSingIn() {
        buttonForSingIn.click();
    }

    public void pressNextButton() {
        nextButton.click();
    }

}
