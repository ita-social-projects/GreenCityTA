package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.Adress.AddressListRoot;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import com.ita.edu.greencity.utils.jdbc.services.UbsCourierService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllLocationWhereCourierIsWorkingTest extends ApiTestRunner {
    private OrderClient orderClient;
    UbsCourierService ubsCourierService = new UbsCourierService();
    private int courierId = Integer.parseInt(ubsCourierService.selectRandomUbsCourier());
    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }
    @Test
    public void successfulGetAllCourierAddressesTest() {
        Response response = orderClient.getAllCourierLocations(courierId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
