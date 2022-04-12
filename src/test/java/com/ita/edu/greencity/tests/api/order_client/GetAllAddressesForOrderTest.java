package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.adress.AddressListRoot;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import com.ita.edu.greencity.utils.jdbc.services.UbsUserAddressService;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetAllAddressesForOrderTest extends ApiTestRunner {
    private OrderClient orderClient;

    private OrderClient unauthorizedOrderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
        unauthorizedOrderClient = new OrderClient();
    }

    @Description("Checks if GetAllAddressesForOrder response is 200 and if addresses in response really exists")
    @Test
    public void successfulGetAllAddressesForOrderTest() {
        UbsUserAddressService ubsUserAddressService = new UbsUserAddressService();
        Response response = orderClient.getAllAddressesForOrder();

       AddressListRoot addressList = response.then().extract().as(AddressListRoot.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        addressList.getAddressList().stream().forEach(x->softAssert.assertTrue(ubsUserAddressService.checkIfAddressExists(x.getStreet())));
        softAssert.assertAll();
    }
    @Test
    public void unauthorizedGetAllAddressesForOrderTest() {
        Response response = unauthorizedOrderClient.getAllAddressesForOrder();
        String message = response.asString();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401);
        softAssert.assertTrue(message.contains("N/A"));
        softAssert.assertAll();
    }
}
