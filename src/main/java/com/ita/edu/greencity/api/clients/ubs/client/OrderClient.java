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

    @Step("post request {this.baseApiURL}/receiveLiqPayPayment")
    public Response receiveLiqPayPayment(String data, String signature) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .contentType("multipart/form-data")
                .multiPart("data", data)
                .multiPart("signature", signature)
                .log().all()
                .when()
                .post(String.format("%s/receiveLiqPayPayment", baseApiURL));

    }
}
