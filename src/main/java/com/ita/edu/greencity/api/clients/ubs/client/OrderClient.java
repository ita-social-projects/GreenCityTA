package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {

    private final String authToken;

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
    }
}
