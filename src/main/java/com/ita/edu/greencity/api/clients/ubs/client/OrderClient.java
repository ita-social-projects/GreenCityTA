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

    @Step("post request {this.baseApiURL}/ubs/findAll-order-address")
    public Response getAllAddressesForOrder() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/findAll-order-address", baseApiURL));
    }

    @Step("post request {this.baseApiURL}/ubs/courier/{courierId}")
    public Response getAllCourierLocations(String courierId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/courier/" + courierId, baseApiURL));
    }

        @Step("post request {this.baseApiURL}/processOrder")
        public Response processUserOrder(UserOrder userOrder){
            return preparedRequest()
                    .header("Authorization", String.format("Bearer %s", authToken))
                    .body(userOrder)
                    .log().all()
                    .when()
                    .post(String.format("%s/processOrder", baseApiURL));
        }
}

