package com.ita.edu.greencity.ui.pages.header;

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

    public void clickSignIn() {
        signIn.click();
    }

    public void clickSignUp() {
        signUp.click();
    }
}
