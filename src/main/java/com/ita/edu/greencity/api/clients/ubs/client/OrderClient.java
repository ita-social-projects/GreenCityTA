package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import io.restassured.response.Response;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {

    private String authToken;

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
    }

    public OrderClient() throws IOException {
        super();
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
