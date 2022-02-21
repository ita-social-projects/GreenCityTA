package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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

    @Step("Delete the order")
    public UbsHomePage clickOnDeleteButton() {
        deleteButton.click();
        return new UbsHomePage(driver);
    }

    @Step("Save the order")
    public SuccessfulSavingPage clickOnSaveButton() {
        sleep(3000);
        saveButton.click();
        loadData();
       // sleep(15000);

        return new SuccessfulSavingPage(driver);
    }

    public SuccessfulSavingPage loadData() {
        while (true) {
            try {
                driver.findElement(By.xpath("//span[contains(@class,'spinner')]"));
            } catch (Exception e) {
                return new SuccessfulSavingPage(driver);
            }
            sleep(500);
        }
    }

    @Step("Close saving pop-up")
    public OrderPageConfirmation clickOnCloseButton() {
        closeButton.click();
        return new OrderPageConfirmation(driver);
    }

}
