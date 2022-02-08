package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Operation3dSecurePopUp extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@type = 'submit']")
    private WebElement continueButton;
    @FindBy(how = How.XPATH, using = "//div[@class = 'page-section page-section-header']/h1")
    private WebElement pageTitle;
    @FindBy(how = How.XPATH, using = "//a[text() ='link']")
    private WebElement linkForRedirection;

    public Operation3dSecurePopUp(WebDriver driver) {
        super(driver);
    }

    @Step("Follow the link")
    public Operation3dSecurePopUp clickOnTheLink() {
        linkForRedirection.click();
        sleep(5000);
        switchToNewTab();
        return this;
    }

    @Step("Press continue button")
    public SuccessfulOrderPage clickOnContinueButton() {
        continueButton.click();
        sleep(5000);
        return new SuccessfulOrderPage(driver);
    }

    @Step("Read the page title")
    public String getTextFromPageTitle() {
        return pageTitle.getText();
    }

}
