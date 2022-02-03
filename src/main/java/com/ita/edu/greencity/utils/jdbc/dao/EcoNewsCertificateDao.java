package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsCertificateEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EcoNewsCertificateDao {

    public void deleteCertificateByCode(String code) {
        Statement statement = ManagerDao.getUbs().getStatement();
        try {
            statement.executeUpdate(String.format(EcoNewsCertificateEntity.DELETE_CERTIFICATE,code));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }

    public void insertCertificate(String codeValue,String statusValue, String expiration_dateValue, int pointsValue) {
        Statement statement = ManagerDao.getUbs().getStatement();
        try {
            statement.executeUpdate(String.format(EcoNewsCertificateEntity.INSERT_NEW_CERTIFICATE,codeValue,statusValue,expiration_dateValue,String.valueOf(pointsValue)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
    }


}
