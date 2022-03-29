package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.order_cancellation.OrderCancellationReason;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {

    private final String authToken;

    public OrderClient() throws IOException {
        super();
        authToken = null;
    }

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
    }

    @Step("get request {this.baseApiURL}/order/{id}/cancellation ")
    public Response getOrderCancellationReason(int id) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order/%d/cancellation", baseApiURL, id));
    }

    @Step("post request {this.baseApiURL}/order/{id}/cancellation ")
    public Response postOrderCancellationReason(OrderCancellationReason orderCancellationReason, int id) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(orderCancellationReason)
                .log()
                .all()
                .when()
                .post(String.format("%s/order/%d/cancellation/", baseApiURL, id));
    }
}
