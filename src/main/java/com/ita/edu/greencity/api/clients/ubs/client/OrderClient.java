package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.clients.user.sign_in.SignInClient;
import com.ita.edu.greencity.api.models.ubs.order.process_order.SuccessfulOrder;
import com.ita.edu.greencity.api.models.ubs.order.process_order.UserOrder;
import com.ita.edu.greencity.api.models.user.SuccessSignIn;
import com.ita.edu.greencity.api.models.user.UserCredentials;
import io.qameta.allure.Step;
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
}

