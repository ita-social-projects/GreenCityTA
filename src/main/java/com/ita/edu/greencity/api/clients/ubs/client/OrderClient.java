package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.order_cancellation.OrderCancellationReason;
import com.ita.edu.greencity.api.models.ubs.order.process_order.UserOrder;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import java.io.IOException;

public class OrderClient extends BaseClientUBS {

    private String authToken;

    public OrderClient() throws IOException {
        super();
        authToken = null;
    }

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
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


    @Step("post request {this.baseApiURL}/processOrder")
    public Response processUserOrder(UserOrder userOrder) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(userOrder)
                .log().all()
                .when()
                .post(String.format("%s/processOrder", baseApiURL));
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

