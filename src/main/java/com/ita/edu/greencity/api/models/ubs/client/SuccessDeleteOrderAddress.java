package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

import java.util.List;

@Data
public class SuccessDeleteOrderAddress {
    private List<List<DeleteOrderAddress>> addressList;
}
