package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UbsUserOrderHistory extends BasePage {

    public UbsUserOrderHistory(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'main_header')]/*[contains(@class, 'btn_pay')]")
    private WebElement newOrderButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '0')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement currentOrdersTabButton;

    public OrderDetailsPage clickOnNewOrderButton() {
        newOrderButton.click();
        return new OrderDetailsPage(driver);
    }

    public UbsUserOrders clickOnCurrentOrdersButton() {
        currentOrdersTabButton.click();
        return new UbsUserOrders(driver);
    }

    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }
}
