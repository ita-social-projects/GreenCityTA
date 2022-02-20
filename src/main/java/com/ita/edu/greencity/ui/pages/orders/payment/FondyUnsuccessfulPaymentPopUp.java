package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FondyUnsuccessfulPaymentPopUp extends BasePage {

    @FindBy(how = How.XPATH, using = "//h5[@class = 'f-modal-title']")
    private WebElement errorMessageTitle;
    @FindBy(how = How.XPATH, using = "//div[@class = 'f-modal-body']/p")
    private WebElement errorMessageBody;
    @FindBy(how = How.XPATH, using = "//button[@aria-label='Close']")
    private WebElement closeButton;

    public FondyUnsuccessfulPaymentPopUp(WebDriver driver) {
        super(driver);
    }

    @Step("Get title text from pop-up window which appears after declined payment")
    public String getTextFromPopUpTitle() {
        return errorMessageTitle.getText();
    }

    @Step("Get body text from pop-up window which appears after declined payment")
    public String getTextFromPopUpBody() {
        return errorMessageBody.getText();
    }

    @Step("Close pop-up window")
    private PaymentByFondyPage clickOnCloseButton() {
        closeButton.click();
        return new PaymentByFondyPage(driver);
    }

}
