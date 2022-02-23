package com.ita.edu.greencity.api.clients.ubs;

import com.ita.edu.greencity.api.clients.BaseClient;

import java.io.IOException;

public class ClientClient extends BaseClient {


    public ClientClient() throws IOException {
        super();
        this.baseApiURL += "client/";

    }
}
