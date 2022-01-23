package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSignedOutComponent extends HeaderComponent {

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-header-sign-in']")
    private WebElement signIn;

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-header_sign-up-btn']")
    private WebElement signUp;

    public HeaderSignedOutComponent(WebDriver driver) {
        super(driver);
    }

    public SignInComponent clickSignIn() {
        signIn.click();
        return new SignInComponent(driver);
    }

    public SignUpComponent clickSignUp() {
        signUp.click();
        return new SignUpComponent(driver);
    }
}
