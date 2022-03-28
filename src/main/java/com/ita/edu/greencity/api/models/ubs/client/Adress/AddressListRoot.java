package com.ita.edu.greencity.api.models.ubs.client.Adress;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AddressListRoot {
    private ArrayList<AddressList> addressList;
    public AddressListRoot() {
    }
}
