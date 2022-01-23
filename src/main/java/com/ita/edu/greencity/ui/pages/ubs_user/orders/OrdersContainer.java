package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrdersContainer {

    private WebElement rootElement;

    public OrdersContainer(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    @FindBy(how = How.XPATH, using = ".//*[contains(@src, 'ubs-admin-orders/arrow_up.svg')]")
    private WebElement moreInformationArrowUp;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'empty_card-id')]")
    private WebElement orderId;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'empty_card-date')]")
    private WebElement orderDate;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'empty_card-status')]")
    private WebElement orderStatus;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'empty_card-paymentStatus')]")
    private WebElement orderPaymentStatus;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_sum')]")
    private WebElement orderPaymentAmount;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_cancel')]")
    private WebElement cancelOrderButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_pay')]")
    private WebElement payForOrderButton;

    public WebElement getMoreOrderInfo() {
        return moreInformationArrowUp;
    }

    public String getOrderId() {
        return orderId.getText();
    }

    public String getOrderDate() {
        return orderDate.getText();
    }

    public String getOrderStatus() {
        return orderStatus.getText();
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus.getText();
    }

    public String getOrderPaymentAmount() {
        return orderPaymentAmount.getText();
    }

    public WebElement getCancelOrderButton() {
        return cancelOrderButton;
    }

    public WebElement getPayForOrderButton() {
        return payForOrderButton;
    }

}
