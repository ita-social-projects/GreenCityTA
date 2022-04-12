package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.process_order_Fondy.OrderFondy;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class ClientClient extends BaseClientUBS {

    private final String authToken;

    public ClientClient() throws IOException {
        super();
        authToken = null;
        this.baseApiURL += "/client";
    }

    public ClientClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
        this.baseApiURL += "/client";
    }

    @Step("get request {this.baseApiURL}/users-pointsToUse ")
    public Response getUserBonuses() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/users-pointsToUse", baseApiURL));

    }

    @Step("post request {this.baseApiURL}/processOrderFondy")
    public Response processOrderFondy(OrderFondy orderFondy) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(orderFondy)
                .log().all()
                .when()
                .post(String.format("%s/processOrderFondy", baseApiURL));
    }

}
