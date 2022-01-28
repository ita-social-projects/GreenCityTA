package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class OrdersContainer {

    private WebDriver driver;

    private DefaultElementLocatorFactory parentContext;

    private WebElement rootElement;

    public OrdersContainer(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @FindBy(how = How.XPATH, using = ".//*[contains(@src, 'ubs-admin-orders/arrow_up.svg')]")
    private WebElement moreOrderInfo;

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
        return moreOrderInfo;
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
        return getPayForOrderButton();
    }

    public WebElement getPayForOrderButton() {
        return payForOrderButton;
    }

}
