package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPageConfirmation extends BasePage {

    @FindBy(xpath = "//mat-step-header[@aria-posinset=1]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement orderDetailsButton;
    @FindBy(xpath = "//mat-step-header[@aria-posinset=2]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement personalDataButton;
    @FindBy(xpath = "//li[contains(@class,'services-list_item') ]/span[text() ='Safe waste']/../span[contains(@class,'orderSum')]")
    private WebElement safeWasteTotal;
    @FindBy(xpath = "//li[contains(@class,'services-list_item') ]/span[text() ='Textile waste']/following-sibling::span[contains(text(),'120')]/../span[contains(@class,'orderSum')]")
    private WebElement textileWasteVolume120Total;
    @FindBy(xpath = "//li[contains(@class,'services-list_item') ]/span[text() ='Textile waste']/following-sibling::span[text() ='20 l']/../span[contains(@class,'orderSum')]")
    private WebElement textileWasteVolume20Total;
    @FindBy(xpath = "//p[@class = 'total-content' ]/span[text() ='Order amount:  ']/following-sibling::span/strong")
    private WebElement orderAmount;
    @FindBy(xpath = "//p[@_ngcontent-gmh-c425]/span[text() ='Amount due:  ']/following-sibling::span/strong")
    private WebElement amountDue;
    @FindBy(xpath = "//select[contains(@class, 'payment-select shadow-none')]")
    private WebElement paymentChooser;
    @FindBy(xpath = "//button[contains(@class,'btn-back ng-star-inserted')]")
    private WebElement backButton;
    @FindBy(xpath = "//button[contains(@class,'cansel-button btn-main')]")
    private WebElement cancelButton;
    @FindBy(xpath = "//button[@class = 'primary-global-button btn']")
    private WebElement orderButton;

    public OrderPageConfirmation(WebDriver driver) {
        super(driver);
    }

    public void clickOnOrderDetailsButton() {
        orderDetailsButton.click();
    }

    public void clickOnPersonalDataButton() {
        personalDataButton.click();
    }

    public String getSafeWasteTotalText() {
        return safeWasteTotal.getText();
    }

    public String getTextileWasteVolume120TotalText() {
        return textileWasteVolume120Total.getText();
    }

    public String getTextileWasteVolume20TotalText() {
        return textileWasteVolume20Total.getText();
    }

    public String getOrderAmountText() {
        return orderAmount.getText();
    }

    public String getAmountDueText() {
        return amountDue.getText();
    }

    public void clickOnPaymentChooser() {
        paymentChooser.click();
    }

    public WebElement paymentOption(String value) {
        return driver.findElement(By.xpath("//select[contains(@class, 'payment-select shadow-none')]/option[@value = '" + value + "']"));
    }

    public void choosePaymentOption() {
        paymentOption("Fondy").click();
    }

    public void clickOnBackButton() {
        backButton.click();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }

    public void clickOnOrderButton() {    //redirect to another page   ?
        orderButton.click();
    }


}
