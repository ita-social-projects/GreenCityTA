package com.ita.edu.greencity.ui.pages.ubs_user.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrdersContainer {

    private WebElement rootElement;

    public OrdersContainer(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public WebElement getMoreOrderInfo() {
        return rootElement.findElement(By.xpath(".//*[contains(@src, 'ubs-admin-orders/arrow_up.svg')]"));
    }

    public String getOrderId() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'empty_card-id')]")).getText();
    }

    public String getOrderDate() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'empty_card-date')]")).getText();
    }

    public String getOrderStatus() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'empty_card-status')]")).getText();
    }

    public String getOrderPaymentStatus() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'empty_card-paymentStatus')]")).getText();
    }

    public String getOrderPaymentAmount() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'order_sum')]")).getText();
    }

    public WebElement getCancelOrderButton() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'btn_cancel')]"));
    }

    public WebElement getPayForOrderButton() {
        return rootElement.findElement(By.xpath(".//*[contains(@class, 'btn_pay')]"));
    }

}
