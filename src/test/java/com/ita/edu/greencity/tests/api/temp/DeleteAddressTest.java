package com.ita.edu.greencity.tests.api.temp;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class DeleteAddressTest extends ApiTestRunner {
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }
}
