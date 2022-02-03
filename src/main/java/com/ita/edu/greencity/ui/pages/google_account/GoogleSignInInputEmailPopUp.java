package com.ita.edu.greencity.ui.pages.google_account;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSignInInputEmailPopUp extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id='identifierId']")
    private WebElement emailInputField;

    @FindBy(how = How.XPATH, using = "//*[@id='identifierNext']//button/span")
    private WebElement continueButton;

    public GoogleSignInInputEmailPopUp(WebDriver driver) {
        super(driver);
    }

    public GoogleSignInInputEmailPopUp inputEmailIntoField(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public GoogleSignInInputPasswordPopUp clickOnContinueButton() {
        sleep(500);
        continueButton.click();
        return new GoogleSignInInputPasswordPopUp(driver);
    }
}
