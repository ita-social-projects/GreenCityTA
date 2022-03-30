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

    @Step("get request {this.baseApiURL}/getFondyStatus")
    public Response getFondyStatus(int orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getFondyStatus/%d", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getFondyStatus")
    public Response getFondyStatus(String orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getFondyStatus/%s", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getLiqPayStatus")
    public Response getLiqPayStatus(int orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getLiqPayStatus/%d", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getLiqPayStatus")
    public Response getLiqPayStatus(String orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getLiqPayStatus/%s", baseApiURL, orderId));
    }
}
