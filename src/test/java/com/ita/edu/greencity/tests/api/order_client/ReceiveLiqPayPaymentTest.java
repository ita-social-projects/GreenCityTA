package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ReceiveLiqPayPaymentTest extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    public void successfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment(provider.getData(), provider.getSignature());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 302);
        softAssert.assertAll();
    }

    @Test
    public void unsuccessfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment("qazwsxedc", "qwedfdsasdf");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();
    }

}
