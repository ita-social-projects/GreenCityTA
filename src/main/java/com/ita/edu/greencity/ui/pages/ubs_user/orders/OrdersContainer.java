package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import io.qameta.allure.Step;
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


    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-expansion-panel-content')]")
    private WebElement orderDetails;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-num')]")
    private WebElement orderId;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-date')]")
    private WebElement orderDate;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-status')]")
    private WebElement orderStatus;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-paymentStatus')]")
    private WebElement paymentStatus;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'order_list-paymentAmount')]")
    private WebElement paymentAmount;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_cancel')]")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'btn_pay')]")
    private WebElement payButton;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class, 'mat-expansion-indicator')]")
    protected WebElement orderDetailsArrowUp;

    @Step("get order id")
    public String getOrderId() {
        return orderId.getText();
    }

    @Step("get order date")
    public String getOrderDate() {
        return orderDate.getText();
    }

    @Step("get order status")
    public String getOrderStatus() {
        return orderStatus.getText();
    }

    @Step("get order payment status")
    public String getPaymentStatus() {
        return paymentStatus.getText();
    }

    @Step("get order payment amount")
    public String getPaymentAmount() {
        return paymentAmount.getText();
    }

    @Step("click on cancel order button")
    public CancelPopUp clickOnCancelButton() {
        cancelButton.click();
        return new CancelPopUp(driver);
    }

    @Step("click on pay for order button")
    public PaymentPopUp clickOnPayButton() {
        payButton.click();
        return new PaymentPopUp(driver);
    }

    @Step("get order details")
    public OrderDetails clickOnOrderDetailsArrowDown() {
        orderDetailsArrowUp.click();
        return new OrderDetails(driver, orderDetails);
    }

    @Step("get orders page")
    public UbsUserOrders getUbsUserOrdersPage() {
        return new UbsUserOrders(driver);
    }

}
