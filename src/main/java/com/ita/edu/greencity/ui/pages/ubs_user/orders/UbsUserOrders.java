package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import io.qameta.allure.Step;
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

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '0')][contains(@class, 'mat-ripple')]")
    private WebElement currentOrdersTab;

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '0')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement currentOrdersTabButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '-1')][contains(@class, 'mat-ripple')]")
    private WebElement orderHistoryTab;

    @FindBy(how = How.XPATH, using = ".//*[contains(@tabindex, '-1')]/*[contains(@class, 'mat-tab-label-content')]")
    private WebElement orderHistoryTabButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-accordion')]/*[contains(@class, 'mat-expansion-panel')]")
    private List<WebElement> orders;


    @Step("get element of new order button")
    public WebElement getNewOrderButton() {
        return newOrderButton;
    }

    @Step("get label text that indicates a blank page")
    public String getEmptyOrdersPageLabel() {
        return emptyOrdersPageLabel.getText();
    }

    @Step("get element of current orders tab button")
    public WebElement getCurrentOrdersTabButton() {
        return currentOrdersTabButton;
    }

    @Step("get element of current orders tab")
    public WebElement getCurrentOrdersTab() {
        return currentOrdersTab;
    }

    @Step("get element of order history tab button")
    public WebElement getOrderHistoryTabButton() {
        return orderHistoryTabButton;
    }

    @Step("get element of order history tab")
    public WebElement getOrderHistoryTab() {
        return orderHistoryTab;
    }

    @Step("click on new order button")
    public OrderDetailsPage clickOnNewOrderButton() {
        newOrderButton.click();
        return new OrderDetailsPage(driver);
    }

    @Step("click on order history tab button")
    public UbsUserOrderHistory clickOnOrderHistoryTabButton() {
        orderHistoryTabButton.click();
        return new UbsUserOrderHistory(driver);
    }

    @Step("push all orders to orders container")
    private List<OrdersContainer> putElementsIntoContainer() {

        List<OrdersContainer> ordersContainerList = new ArrayList<>();

        for (WebElement element : orders) {
            ordersContainerList.add(new OrdersContainer(driver, element));
        }

        return ordersContainerList;
    }

    @Step("get order from container")
    public OrdersContainer getOrder(String numberOfOrder) {

        return putElementsIntoContainer()
                .stream()
                .filter(element -> element.getOrderId().equals(numberOfOrder))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Step("get ubs user page")
    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }

    @Step("get header")
    public HeaderSignedInComponent getHeader() {
        return new HeaderSignedInComponent(driver);
    }

}
