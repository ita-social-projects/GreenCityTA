package com.ita.edu.greencity.tests.api.temp;

import com.ita.edu.greencity.api.clients.ubs.calient.ClientClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.SuccessUserPointToUse;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class tempTests extends ApiTestRunner {
    private Authorization authorization;
    private ClientClient clientClient;
    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getAdminEmail(), provider.getAdminPassword());
        clientClient = new ClientClient(authorization.getToken());
    }

    @Test
    public void test1(){

        SuccessUserPointToUse userBonuses = clientClient.getUserBonuses();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(userBonuses.getUserBonuses(), 0);
        softAssert.assertEquals(userBonuses.getUbsUserBonuses().size(), 0);
        softAssert.assertAll();
    }

}
