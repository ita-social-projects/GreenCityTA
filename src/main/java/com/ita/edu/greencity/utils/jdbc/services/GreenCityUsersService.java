package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.AdminCustomersDao;
import com.ita.edu.greencity.utils.jdbc.dao.GreenCityUsersDao;

public class GreenCityUsersService {
    private GreenCityUsersDao greenCityUsersDao;

    public GreenCityUsersService() {
        this.greenCityUsersDao = new GreenCityUsersDao();
    }
    public int selectUsersIdByEmail(String email) {
        return greenCityUsersDao.selectUsersIDbyEmail(email);
    }

}
