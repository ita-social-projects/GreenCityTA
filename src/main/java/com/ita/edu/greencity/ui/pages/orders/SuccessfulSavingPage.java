package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SuccessfulSavingPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//simple-snack-bar[@class = 'mat-simple-snackbar ng-star-inserted']/span")
    private WebElement successfulSavingAlert;
    @FindBy(how = How.XPATH, using = "//button[@class = 'btn primary-global-button']")
    private WebElement toPersonalAccountButton;
    @FindBy(how = How.XPATH, using = "//a[@routerlink]")
    private WebElement makeOtherOrderButton;

    public SuccessfulSavingPage(WebDriver driver) {
        super(driver);
    }

    public String getTextFromSuccessfulSavingAlert() {
        sleep(4000);
        return successfulSavingAlert.getText();
    }

    public UbsUserOrders clickToPersonalAccountButton() {
        toPersonalAccountButton.click();
        return new UbsUserOrders(driver);
    }

    public SelectRegion clickOnMakeOtherOrderButton() {
        makeOtherOrderButton.click();
        return new SelectRegion(driver);
    }

}
