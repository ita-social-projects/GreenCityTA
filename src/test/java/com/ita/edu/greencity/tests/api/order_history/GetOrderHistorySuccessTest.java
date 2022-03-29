package com.ita.edu.greencity.tests.api.order_history;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.order_history.SuccessOrderHistory;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderHistorySuccessTest extends ApiTestRunner {

    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check success getting of order history by orderId")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderHistory(89, 2);
        SuccessOrderHistory[] orderHistory = response.as(SuccessOrderHistory[].class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code isn't right!");
        softAssert.assertEquals(orderHistory.length, 1, "Size of records in response isn't right!");
        softAssert.assertEquals(orderHistory[0].getId(), 438, "Id isn't right! ");
        softAssert.assertEquals(orderHistory[0].getEventDate(), "2022-02-14T17:05:03.279688", "EventDate isn't right! ");
        softAssert.assertEquals(orderHistory[0].getEventName(), "Статус Замовлення - Сформовано", "EventName isn't right! ");
        softAssert.assertEquals(orderHistory[0].getAuthorName(), "Клієнт", "AuthorName isn't right! ");
        softAssert.assertAll();
    }
}
