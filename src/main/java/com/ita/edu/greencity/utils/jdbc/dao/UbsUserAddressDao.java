package com.ita.edu.greencity.utils.jdbc.dao;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsCertificateEntity;
import com.ita.edu.greencity.utils.jdbc.entity.UbsUserAddressEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UbsUserAddressDao {
    public List<String> checkIfAddressExists(String address) {
        Statement statement = ManagerDao.getUbs().getStatement();
        List<String> rows = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format(UbsUserAddressEntity.CHECK_IF_ADDRESS_EXISTS, address,address));
            while (resultSet.next()) {
                rows.add(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManagerDao.closeStatement(statement);
        return rows;
    }
}
