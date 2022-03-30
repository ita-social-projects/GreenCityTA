package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ReceiveLiqPayPaymentTest extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    public void successfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment(provider.getCorrectData(), provider.getCorrectSignature());
        Assert.assertEquals(response.getStatusCode(), 302);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_MOVED_TEMP);
    }

    @Test
    public void unsuccessfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment(provider.getIncorrectData(), provider.getIncorrectSignature());
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST);
    }

}
