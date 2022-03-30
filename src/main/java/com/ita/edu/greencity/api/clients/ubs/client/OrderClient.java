package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.ReqUpdateRecipientsData;
import com.ita.edu.greencity.api.models.ubs.client.SuccessReqSaveOrderAddress;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {
    SuccessReqSaveOrderAddress successReqSaveOrderAddress = new SuccessReqSaveOrderAddress();
    SuccessReqSaveOrderAddress addressList = new SuccessReqSaveOrderAddress();
    ReqUpdateRecipientsData reqUpdateRecipientsData = new ReqUpdateRecipientsData();
    ReqUpdateRecipientsData upDataList = new ReqUpdateRecipientsData();

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

    @Step("get request {this.baseApiURL}/certificate ")
    public Response getCertificatesInfo(String certificate) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("certificate/%s", certificate));

    }

    @Step("get request {this.baseApiURL}/delete-order-address ")
    public Response deleteOrderAddressById(Long id) {

        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .post(String.format("%s/delete-order-address", id));

    }

    @Step("post request {this.baseApiURL}/save-order-address")
    public Response saveOrderAddressByRequest(SuccessReqSaveOrderAddress.AddressList addressList) {
        SuccessReqSaveOrderAddress successReqSaveOrderAddress = new SuccessReqSaveOrderAddress();
        RequestSpecification requestSpecification = preparedRequest();
        if(authToken !=null){
            requestSpecification.header("Authorization", String.format("Bearer %s", authToken));
        }
        return requestSpecification
                .body(addressList)
                .post(String.format("%s/save-order-address", baseApiURL));
    }


    @Step("put request {this.baseApiURL}/update-recipients-data")
    public Response reqUpdateRecipientsData(ReqUpdateRecipientsData upDataList) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(upDataList)
                .post(String.format("%s/update-recipients-data", baseApiURL));

    }

}
