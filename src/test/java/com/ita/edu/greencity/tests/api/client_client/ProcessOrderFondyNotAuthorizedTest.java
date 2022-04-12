package com.ita.edu.greencity.tests.api.client_client;

import com.ita.edu.greencity.api.clients.ubs.client.ClientClient;
import com.ita.edu.greencity.api.models.ubs.client.process_order_Fondy.OrderFondy;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ProcessOrderFondyNotAuthorizedTest extends ApiTestRunner {

    @Description("[API] verify 401 status code for unauthorized user")
    @Test()
    public void unauthorizedUserTest() throws IOException {
        OrderFondy orderFondy = new OrderFondy(null, 548L, 10L);
        Response response = new ClientClient().processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Mismatched HTTP status codes");
        softAssert.assertEquals(response.then().extract().body().asString(), "<html><body><h2>Error Page</h2><div>Status code: <b>401</b></div><div>Exception Message: <b>N/A</b></div><body></html>", "Mismatched response strings");
        softAssert.assertAll();
    }
}
