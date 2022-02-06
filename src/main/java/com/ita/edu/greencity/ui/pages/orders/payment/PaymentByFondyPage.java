package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PaymentByFondyPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//b[@class = 'f-amount']")
    private WebElement totalPay;
    @FindBy(how = How.XPATH, using = "//span[@class = 'f-currency']")
    private WebElement currency;
    @FindBy(how = How.XPATH, using = "//select[@dir]")
    private WebElement languageChooser;
    @FindBy(how = How.XPATH, using = "//select[@dir]/option")
    private List<WebElement> listOfLanguages;
    @FindBy(how = How.XPATH, using = "//input[@name = 'f-card_number']")
    private WebElement cardNumber;
    @FindBy(how = How.XPATH, using = "//input[@name = 'f-expiry_date']")
    private WebElement expiryDate;
    @FindBy(how = How.XPATH, using = "//input[@name = 'f-cvv2']")
    private WebElement CVV2;
    @FindBy(how = How.XPATH, using = "//input[@name = 'f-email']")
    private WebElement emailField;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'f-btn-success-bg-lighten')]")
    private WebElement payButton;
    @FindBy(how = How.XPATH, using = "//div[@class = 'f-error']")
    private WebElement emailErrorMessage;

    public PaymentByFondyPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the total amount of order to pay")
    public String getTotalPay() {
        sleep(6000);
        return totalPay.getText();
    }

    @Step("Get currency of order payment")
    public String getCurrency() {
        return currency.getText();
    }

    private void clickOnLanguageChooser() {
        languageChooser.click();
    }
    private List<WebElement> getListOfLanguages() {
        return listOfLanguages;
    }

    @Step("Select language from available options")
    public PaymentByFondyPage chooseLanguage(String language) {
        sleep(5000);
        clickOnLanguageChooser();
        for (WebElement option : getListOfLanguages()) {
            if (option.getText().equalsIgnoreCase(language)) {
                option.click();
                break;
            }
        }
        return this;
    }

    @Step("Enter card number to make a payment")
    public PaymentByFondyPage cardNumberInput(String value) {
        sleep(5000);
        cardNumber.sendKeys(value);
        return this;
    }

    @Step("Enter card expiry date to make a payment")
    public PaymentByFondyPage expiryDateInput(String value) {
        expiryDate.sendKeys(value);
        return this;
    }

    @Step("Enter card CVV2 value to make a payment")
    public PaymentByFondyPage CVV2Input(String value) {
        CVV2.sendKeys(value);
        return this;
    }

    @Step("Enter user's email address")
    public PaymentByFondyPage emailInput(String value) {
        emailField.sendKeys(value);
        return this;
    }

    @Step("Click on page title to focus")
    public PaymentByFondyPage unfocus() {
        totalPay.click();
        return this;
    }

    @Step("Click on email input field to focus on it")
    public PaymentByFondyPage focusToEmailField() {
        emailField.click();
        return this;
    }

    @Step("Pay for the order")
    public Operation3dSecurePopUp clickOnPayButton() {
        payButton.click();
        return new Operation3dSecurePopUp(driver);
    }

    @Step("Get error message about invalid email address")
    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

}
