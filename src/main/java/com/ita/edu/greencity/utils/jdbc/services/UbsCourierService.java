package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.UbsCourierDao;

public class UbsCourierService {
    private final UbsCourierDao ubsCourierDao;
    public UbsCourierService(){
        ubsCourierDao = new UbsCourierDao();
    }
    public String selectRandomUbsCourier() {
        return ubsCourierDao.getRandomUbsCourier().get(0);
    }
}
