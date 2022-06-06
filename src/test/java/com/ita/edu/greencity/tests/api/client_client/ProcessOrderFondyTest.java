package com.ita.edu.greencity.tests.api.client_client;

import com.ita.edu.greencity.api.clients.ubs.client.ClientClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.process_order_Fondy.OrderFondy;
import com.ita.edu.greencity.api.models.ubs.order.process_order.BadRequest;
import com.ita.edu.greencity.api.models.ubs.order.process_order.SuccessfulOrder;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class ProcessOrderFondyTest extends ApiTestRunner {
    protected final String VALID_CERTIFICATE = "0982-3733";
    protected final String INVALID_CERTIFICATE = "00000000";
    final int firstElementFromBadRequestList = 0;
    private ClientClient clientClient;

    @BeforeClass(description = "user authorization")
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        clientClient = new ClientClient(authorization.getToken());
    }

    @Description("creation of Fondy Order object")
    public OrderFondy createFondyOrderObject() {
        return new OrderFondy(null, 558L, 10L);
    }

    @Description("[API] verify 200 status code for successful Fondy order without certificate")
    @Test
    public void successFondyOrderWithoutCertificateTest() {
        OrderFondy orderFondy = createFondyOrderObject();
        Response response = clientClient.processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Mismatched HTTP status codes");
        SuccessfulOrder successfulOrder = response.then().extract().as(SuccessfulOrder.class);
        softAssert.assertNotNull(successfulOrder.getOrderId(), "Order id is null");
        softAssert.assertTrue(successfulOrder.getOrderId() == 558L);
        softAssert.assertTrue(successfulOrder.getLink().contains("https://pay.fondy.eu/merchants"), "Invalid link");
        softAssert.assertAll();
    }

    @Description("[API] verify 200 status code for successful Fondy order with certificate")
    @Test
    public void successOrderWithCertificateTest() {
        OrderFondy orderFondy = new OrderFondy(List.of(VALID_CERTIFICATE), 558L, 10L);
        Response response = clientClient.processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Mismatched HTTP status codes");
        SuccessfulOrder successfulOrder = response.then().extract().as(SuccessfulOrder.class);
        softAssert.assertNotNull(successfulOrder.getOrderId(), "Order id is null");
        softAssert.assertTrue(successfulOrder.getOrderId() == 558L);
        softAssert.assertTrue(successfulOrder.getLink().contains("https://pay.fondy.eu/merchants"), "Invalid link");
        softAssert.assertAll();
    }

    @Description("[API] verify 400 status code for Fondy order with invalid certificate")
    @Test
    public void invalidCertificateValueTest() {
        OrderFondy orderFondy = new OrderFondy(List.of(INVALID_CERTIFICATE), 558L, 10L);
        Response responseForInvalidCertificateValue = clientClient.processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForInvalidCertificateValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForInvalidCertificateValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");

        List<BadRequest> listWithResponseForInvalidCertificateValue = responseForInvalidCertificateValue.as(new TypeRef<>() {
        });
        softAssert.assertEquals(listWithResponseForInvalidCertificateValue
                .get(firstElementFromBadRequestList).getName(), "certificates[]", "Mismatched invalid certificate names");
        softAssert.assertEquals(listWithResponseForInvalidCertificateValue
                .get(firstElementFromBadRequestList).getMessage(), "This certificate code is not valid", "Mismatched invalid certificate messages");
        softAssert.assertAll();
    }

    @Description("[API] verify 404 status code for Fondy order with invalid order id")
    @Test
    public void invalidOrderIdValueTest() {
        OrderFondy orderFondy = createFondyOrderObject();
        orderFondy.setOrderId(1111L);
        Response responseForIncorrectOrderIdValue = clientClient.processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForIncorrectOrderIdValue.getStatusCode(), 404, "Mismatched status codes");
        softAssert.assertEquals(responseForIncorrectOrderIdValue.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Mismatched HTTP status codes");
        BadRequest badRequestForIncorrectOrderIdValue = responseForIncorrectOrderIdValue.as(BadRequest.class);
        softAssert.assertEquals(badRequestForIncorrectOrderIdValue.getMessage(), "Order with current id does not exist: ", "Mismatched messages");
        softAssert.assertAll();
    }

    @Description("[API] verify 400 status code for Fondy order with invalid number of points to use")
    @Test
    public void invalidPointsToUseValueTest() {
        OrderFondy orderFondy = createFondyOrderObject();
        orderFondy.setPointsToUse(1000L);
        Response responseForIncorrectPointsToUseValue = clientClient.processOrderFondy(orderFondy);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForIncorrectPointsToUseValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForIncorrectPointsToUseValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        BadRequest badRequestForIncorrectPointsToUseValue = responseForIncorrectPointsToUseValue.as(BadRequest.class);
        softAssert.assertEquals(badRequestForIncorrectPointsToUseValue.getMessage(), "User doesn't have enough bonus points.", "Mismatched messages");
        softAssert.assertAll();
    }

}
