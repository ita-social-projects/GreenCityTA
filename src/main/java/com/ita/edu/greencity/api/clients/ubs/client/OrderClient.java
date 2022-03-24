package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {

    private final String authToken;

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
    }

    public OrderClient() throws IOException {
        super();
        authToken = null;
    }

    @Step("get request {this.baseApiURL}/order_history ")
    public Response getOrderHistory(int orderId, int languageId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order_history/%d?lang=%d", baseApiURL, orderId, languageId));
    }

    @Step("get request {this.baseApiURL}/order-details ")
    public Response getOrderDetails() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order-details", baseApiURL));
    }


    public Response getCertificatesInfo(String certificate) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("certificate/%s", certificate));

    }

    public Response deleteOrderAddressById(Long id) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/delete-order-address", id));

    }
}
