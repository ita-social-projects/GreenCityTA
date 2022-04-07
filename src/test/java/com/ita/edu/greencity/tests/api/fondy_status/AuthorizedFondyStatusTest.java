package com.ita.edu.greencity.tests.api.fondy_status;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.getFondyStatus.SuccessFondyStatus;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.Forbidden;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.NotFound;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AuthorizedFondyStatusTest extends ApiTestRunner {

    private final int PAID_ORDER_ID = 148;
    private final int HALF_PAID_ORDER_ID = 149;
    private final int UNPAID_ORDER_ID = 150;
    private final int NON_EXISTENT_ORDER_ID = 1111111111;
    private final int OTHERS_USER_ORDER_ID = 147;
    private final String INCORRECT_ORDER_ID = "order";
    private final String ERROR_404_MESSAGE = "Order with current id does not exist: ";
    private final String ERROR_403_MESSAGE = "Cannot access another user's payment status";

    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getUserWithOrdersEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] verify successful fondy status")
    @Link("https://jira.softserve.academy/browse/GC-2527")
    public void successfulGettingOfFondyStatus() {
        SoftAssert softAssert = new SoftAssert();

        Response responseOfPaidOrder = orderClient.getFondyStatus(PAID_ORDER_ID);
        SuccessFondyStatus fondyStatusOfPaidOrder = responseOfPaidOrder.as(SuccessFondyStatus.class);
        softAssert.assertEquals(responseOfPaidOrder.getStatusCode(), 200, "Status code is wrong!");
        softAssert.assertEquals(fondyStatusOfPaidOrder.getPaymentStatus(), "PAID", "Incorrect payment status!");

        Response responseOfUnpaidOrder = orderClient.getFondyStatus(UNPAID_ORDER_ID);
        SuccessFondyStatus fondyStatusOfUnpaidOrder = responseOfUnpaidOrder.as(SuccessFondyStatus.class);
        softAssert.assertEquals(responseOfUnpaidOrder.getStatusCode(), 200, "Status code is wrong!");
        softAssert.assertEquals(fondyStatusOfUnpaidOrder.getPaymentStatus(), "UNPAID", "Incorrect payment status!");

        Response responseOfHalfPaidOrder = orderClient.getFondyStatus(HALF_PAID_ORDER_ID);
        SuccessFondyStatus fondyStatusOfHalfPaidOrder = responseOfHalfPaidOrder.as(SuccessFondyStatus.class);
        softAssert.assertEquals(responseOfHalfPaidOrder.getStatusCode(), 200, "Status code is wrong!");
        softAssert.assertEquals(fondyStatusOfHalfPaidOrder.getPaymentStatus(), "HALF_PAID", "Incorrect payment status!");

        softAssert.assertAll();
    }

    @Test
    @Description("[API] verify 404 error with non-existent order")
    public void unsuccessfulGettingOfFondyStatusWithNonExistentOrderTest() {
        Response response = orderClient.getFondyStatus(NON_EXISTENT_ORDER_ID);
        NotFound fondyStatus = response.as(NotFound.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 404, "Status code is wrong!");
        softAssert.assertEquals(fondyStatus.getMessage(), ERROR_404_MESSAGE + NON_EXISTENT_ORDER_ID, "Message is wrong!");
        softAssert.assertAll();
    }

    @Test
    @Description("[API] verify unsuccessful getting of fondy status with another's user order")
    public void unsuccessfulGettingOfFondyStatusWithOthersUserOrderId() {
        Response response = orderClient.getFondyStatus(OTHERS_USER_ORDER_ID);
        Forbidden fondyStatus = response.as(Forbidden.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 403, "Status code is wrong!");
        softAssert.assertEquals(fondyStatus.getMessage(), ERROR_403_MESSAGE, "Message is wrong!");
        softAssert.assertAll();
    }

    @Test
    @Description("[API] verify 400 error with incorrect order id format")
    public void unsuccessfulGettingOfFondyStatusWithIncorrectOrderIdFormat() {
        Response response = orderClient.getFondyStatus(INCORRECT_ORDER_ID);
        Assert.assertEquals(response.getStatusCode(), 400, "Status code is wrong!");
    }
}
