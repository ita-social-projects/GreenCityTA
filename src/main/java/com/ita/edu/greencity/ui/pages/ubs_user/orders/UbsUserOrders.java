package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class UbsUserOrders extends BasePage {

    public UbsUserOrders(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = ".//*[@class = 'if_empty ng-star-inserted']/span")
    private WebElement emptyOrdersPageLabel;


    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'main_header')]/*[contains(@class, 'btn_pay')]")
    private WebElement newOrderButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '-1')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement orderHistoryTabButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'card ng-star-inserted')]/*[contains(@class, 'empty_card')]")
    private List<WebElement> orders;


    public String getEmptyOrdersPageLabel() {
        return emptyOrdersPageLabel.getText();
    }


    public OrderDetailsPage clickOnNewOrderButton() {
        newOrderButton.click();
        return new OrderDetailsPage(driver);
    }

    public UbsUserOrderHistory clickOnOrderHistory() {
        orderHistoryTabButton.click();
        return new UbsUserOrderHistory(driver);
    }

    private List<OrdersContainer> getOrders() {

        List<OrdersContainer> ordersContainerList = new ArrayList<>();

        for (WebElement element : orders) {
            ordersContainerList.add(new OrdersContainer(driver, element));
        }

        return ordersContainerList;
    }

    public OrdersContainer filterOrders(String numberOfOrder) {

        return getOrders()
                .stream()
                .filter(element -> element.getOrderId().equals(numberOfOrder))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }

    public WebElement getNewOrderButton() {
        return newOrderButton;
    }
}
