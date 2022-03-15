package com.ita.edu.greencity.api.clients.ubs.calient;

import com.ita.edu.greencity.api.clients.BaseClient;
import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.SuccessUserPointToUse;
import io.restassured.response.Response;

import java.io.IOException;

public class ClientClient extends BaseClientUBS {

    private final String authToken;

    public ClientClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
        this.baseApiURL += "client/";

    }

    public SuccessUserPointToUse getUserBonuses(){
        Response response = preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%suser-bonuses", baseApiURL));
       return response.as(SuccessUserPointToUse.class);
    }
}
