package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.process_order.*;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessOrderTest extends ApiTestRunner {
    private OrderClient orderClient;
    private Authorization authorization;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        //   orderClient = new OrderClient(authorization.getToken());
    }

    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, null, 1L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 199L, 0L, true}
        };
    }

    @DataProvider(name = "emptyLinkData")
    public Object[][] emptyLinkData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, null, 1L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 199L, 0L, false}
        };
    }

    @DataProvider(name = "incorrectUbsUserIdData")
    public Object[][] incorrectUbsUserIdData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, null, 1L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 111L, 0L, true}
        };
    }


    @DataProvider(name = "smallBagsData")
    public Object[][] smallBagsData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, null, 2L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 199L, 0L, true}
        };
    }

    @DataProvider(name = "incorrectLocationIdData")
    public Object[][] incorrectLocationIdData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, null, 6L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 199L, 0L, true}
        };
    }

    @DataProvider(name = "incorrectCertificateCodeData")
    public Object[][] incorrectCertificateCodeData() {
        return new Object[][]{
                {"12345678", 167L, 3L, 1L, "44444444", 1L, null, null, provider.getEmail(), "John",
                        12L, "Doe", "380631234499", null, null, null, null, 199L, 0L, true}
        };
    }

    @Test(dataProvider = "orderData")
    public void successOrderTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        SuccessfulOrder successfulOrder = response.then().extract().as(SuccessfulOrder.class);
        softAssert.assertNotNull(successfulOrder.getOrderId());
        softAssert.assertTrue(successfulOrder.getLink().contains("https://pay.fondy.eu/merchants/b987e1aa765ebe6d5e76c027acb02cf7ba866d92/default/index.html"));
        softAssert.assertAll();
    }

    @Test(dataProvider = "orderData")
    public void unauthorizedUserTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient();
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401);
        softAssert.assertEquals(response.then().extract().body().asString(), "<html><body><h2>Error Page</h2><div>Status code: <b>401</b></div><div>Exception Message: <b>N/A</b></div><body></html>");
        softAssert.assertAll();
    }

    @Test(dataProvider = "emptyLinkData")
    public void emptyLinkTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        SuccessfulOrder successfulOrder = response.then().extract().as(SuccessfulOrder.class);
        softAssert.assertEquals(successfulOrder.getLink(), null);
        softAssert.assertAll();
    }

    @Test(dataProvider = "incorrectUbsUserIdData")
    public void incorrectUbsUserIdTest(String additionalOrders, Long addressId,
                  Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                  String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                  String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                  Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        BadRequest badRequest = response.as(BadRequest.class);
        softAssert.assertEquals(badRequest.getMessage(), "The set of user data does not exist with id: 111");
        softAssert.assertAll();
    }

    @Test(dataProvider = "smallBagsData")
    public void smallBagsTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        BadRequest badRequest = response.as(BadRequest.class);
        softAssert.assertEquals(badRequest.getMessage(), "Not enough big bags, minimal amount is:20");
        softAssert.assertAll();
    }

    @Test(dataProvider = "incorrectLocationIdData")
    public void incorrectLocationIdTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, List<String> certificates, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                certificates,
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 500);
        softAssert.assertEquals(response.then().extract().body().asString(), "<html><body><h2>Error Page</h2><div>Status code: <b>500</b></div><div>Exception Message: <b>Request processing failed; nested exception is java.lang.NullPointerException</b></div><body></html>");
        softAssert.assertAll();
    }

    @Test(dataProvider = "incorrectCertificateCodeData")
    public void incorrectCertificateCodeTest(String additionalOrders, Long addressId,
                Long amount, Long bagId, String certificate, Long locationId, String orderComment,
                String addressComment, String email, String firstName, Long id, String lastName, String phoneNumber,
                String senderEmail, String senderFirstName, String senderLastName, String senderPhoneNumber,
                Long ubsUserId, Long pointsToUse, Boolean shouldBePaid) throws IOException {
        orderClient = new OrderClient(authorization.getToken());
        UserOrder userOrder = new UserOrder(
                new ArrayList<>(List.of(additionalOrders)),
                addressId,
                new ArrayList<>(List.of(new Bag(amount, bagId))),
                new ArrayList<>(List.of(certificate)),
                locationId,
                orderComment,
                new PersonalData(addressComment, email,
                        firstName, id, lastName, phoneNumber,
                        senderEmail, senderFirstName, senderLastName, senderPhoneNumber,
                        ubsUserId),
                pointsToUse,
                shouldBePaid);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        List<BadRequest> badRequests = response.as(new TypeRef<>() {
        });
        softAssert.assertEquals(badRequests.get(0).getName(), "certificates[]");
        softAssert.assertEquals(badRequests.get(0).getMessage(), "This certificate code is not valid");
        softAssert.assertAll();
    }

}
