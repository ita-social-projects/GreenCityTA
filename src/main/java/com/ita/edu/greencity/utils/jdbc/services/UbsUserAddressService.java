package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.UbsUserAddressDao;
import com.ita.edu.greencity.utils.jdbc.entity.UbsUserAddressEntity;

public class UbsUserAddressService {
    private final UbsUserAddressDao ubsUserAddressDao;

    public UbsUserAddressService() {
        ubsUserAddressDao = new UbsUserAddressDao();

    }

    public final boolean checkIfAddressExists(String address) {
        String value = ubsUserAddressDao.checkIfAddressExists(address).get(0);
        return !value.equals("0");
    }
}
