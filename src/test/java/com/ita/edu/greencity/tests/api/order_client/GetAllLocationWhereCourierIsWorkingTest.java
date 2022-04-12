package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.get_courier_locations.CourierLocationsRoot;
import com.ita.edu.greencity.api.models.ubs.order.get_courier_locations.ErrorMessage;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.utils.jdbc.services.UbsCourierService;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class GetAllLocationWhereCourierIsWorkingTest extends ApiTestRunner {

    private OrderClient orderClient;
    private OrderClient orderClientUnauthorized;
    UbsCourierService ubsCourierService = new UbsCourierService();
    private int correctCourierId = Integer.parseInt(ubsCourierService.selectRandomUbsCourier());
    private int wrongCourierId = TestHelpersUtils.generateRandomWrongCourierIdNumber();

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
        orderClientUnauthorized = new OrderClient();
    }

    @Test
    public void successfulGetAllCourierAddressesTest() {

        Response response = orderClient.getAllCourierLocations(correctCourierId);
        List<CourierLocationsRoot> courierLocations = response.as(new TypeRef<>() {});//Тип данних є масивом тому робимо ліст і тайпреф
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(courierLocations.get(0).getCourierDtos().get(0).getCourierId(),correctCourierId);
        softAssert.assertAll();

    }

    @Test
    public void wrong400GetAllCourierAddressesTest() {
        Response response = orderClient.getAllCourierLocations(wrongCourierId);
        ErrorMessage message = response.as(ErrorMessage.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(message.getMessage(), "Couldn't found courier by id: " + wrongCourierId);
        softAssert.assertAll();
    }

    @Test
    public void wrongUnauthorizedGetAllCourierAddressesTest() throws IOException {
        Response response = orderClientUnauthorized.getAllCourierLocations(correctCourierId);
        String message = response.asString();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401);
       softAssert.assertTrue(message.contains("N/A"));
        softAssert.assertAll();
    }


}
