package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderSavingPopUp extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button submit-cancel']")
    private WebElement deleteButton;
    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button submit-confirm']")
    private WebElement saveButton;
    @FindBy(how = How.XPATH, using = "//button[@class = 'close ng-star-inserted']")
    private WebElement closeButton;
    public OrderSavingPopUp(WebDriver driver) {
        super(driver);
    }

    public UbsHomePage clickOnDeleteButton() {
        deleteButton.click();
        return new UbsHomePage(driver);
    }

    public SuccessfulSavingPage clickOnSaveButton() {
        saveButton.click();
        return new SuccessfulSavingPage(driver);
    }

    public OrderPageConfirmation clickOnCloseButton() {
        closeButton.click();
        return new OrderPageConfirmation(driver);
    }

}
