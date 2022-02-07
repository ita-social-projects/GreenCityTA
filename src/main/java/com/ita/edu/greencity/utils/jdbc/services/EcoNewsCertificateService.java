package com.ita.edu.greencity.utils.jdbc.services;

import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsCertificateDao;
import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsUserActionsDao;
import com.ita.edu.greencity.utils.jdbc.dao.EcoNewsUsersDao;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsCertificateEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUserActionsEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;

import java.util.List;

public class EcoNewsCertificateService {
    private EcoNewsCertificateDao ecoNewsCertificateDao;

    public EcoNewsCertificateService() {
        this.ecoNewsCertificateDao = new EcoNewsCertificateDao();

    }

    public void addCertificate(String codeValue,String statusValue, String expiration_dateValue, int pointsValue) {
        ecoNewsCertificateDao.insertCertificate(codeValue,statusValue,expiration_dateValue,pointsValue);
    }
    public void deleteCertificateByCode(String code) {
        ecoNewsCertificateDao.deleteCertificateByCode(code);
    }
    public String selectRandomUsedCertificate() {
        return ecoNewsCertificateDao.getRandomUsedCertificate().get(0);
    }

}

