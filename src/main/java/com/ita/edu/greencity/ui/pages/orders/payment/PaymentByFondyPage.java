package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
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
    @FindBy(how = How.XPATH, using = "//div[@class = 'f-error']")
    private WebElement emailInputAlert;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'f-btn-success-bg-lighten')]")
    private WebElement payButton;

    public PaymentByFondyPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalPay() {
        return totalPay.getText();
    }

    public String getCurrency() {
        return currency.getText();
    }

    public void clickOnLanguageChooser() {
        languageChooser.click();
    }

    public PaymentByFondyPage chooseLanguage(String value) {
        clickOnLanguageChooser();
        try {
            for (WebElement option : listOfLanguages) {
                if (option.getText().equals(value.trim()))
                    option.click();
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

    public PaymentByFondyPage cardNumberInput(String value) {
        sleep(6000);
        cardNumber.sendKeys(value);
        return this;
    }

    public PaymentByFondyPage expiryDateInput(String value) {
        expiryDate.sendKeys(value);
        return this;
    }

    public PaymentByFondyPage CVV2Input(String value) {
        CVV2.sendKeys(value);
        return this;
    }

    public PaymentByFondyPage emailInput(String value) {
        emailField.sendKeys(value);
        return this;
    }

    public String getTextFromEmailAlert() {
        return emailInputAlert.getText();
    }

    public Operation3dSecurePopUp clickOnPayButton() {
        payButton.click();
        return new Operation3dSecurePopUp(driver);
    }

}
