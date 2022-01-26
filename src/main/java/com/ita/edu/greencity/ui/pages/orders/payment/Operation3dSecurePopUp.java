package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Operation3dSecurePopUp extends BasePage {

    @FindBy(how = How.XPATH, using = "//button")
    private WebElement continueButton;
    @FindBy(how = How.XPATH, using = "//div[@class = 'page-section page-section-header']/h1")
    private WebElement pageTitle;

    public Operation3dSecurePopUp(WebDriver driver) {
        super(driver);
    }

    public SuccessfulOrderPage clickOnContinueButton() {
        sleep(5000);
        continueButton.click();
        return new SuccessfulOrderPage(driver);
    }

    public String getTextFromPageTitle() {
        return pageTitle.getText();
    }

}
