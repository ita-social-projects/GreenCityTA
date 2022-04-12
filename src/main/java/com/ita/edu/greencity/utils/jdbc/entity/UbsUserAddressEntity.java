package com.ita.edu.greencity.utils.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

import static com.ita.edu.greencity.utils.jdbc.entity.EcoNewsCertificateEntity.getEcoNewsCertificateEntity;

enum UbsUserAddressEntityFields{
    ID(1),ACTUAL(2), ADDRESS_COMMENT(3),CITY(4),CITY_EN(5),DISTRICT(6),DISTRICT_EN(7),ENTRANCE_NUMBER(8), HOUSE_CORPUS(9),HOUSE_NUMBER(10),LATITUDE(11),LONGITUDE(12),REGION(13),REGION_EN(14),STATUS(15), STREET(16),STREET_EN(17), USER_ID(18);
    private final int number;
    UbsUserAddressEntityFields(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
public class UbsUserAddressEntity {
    public static final String CHECK_IF_ADDRESS_EXISTS = "SELECT count(1) FROM address  WHERE user_id = 12 and  street = '%s' and status = 'IN_ORDER' or user_id = 12 and  street = '%s' and status = 'NEW'";
private int id;
private String status;
private String street;
private int user_id;

    public UbsUserAddressEntity(int id, String status, String street, int user_id) {
        this.id = id;
        this.status = status;
        this.street = street;
        this.user_id = user_id;
    }

    public UbsUserAddressEntity() {
        this.id = 0;
        this.status = null;
        this.street = null;
        this.user_id = 0;
    }

    public static UbsUserAddressEntity getUbsUserAddressEntity(List<String> row) {
        return new UbsUserAddressEntity()
                .setId(Integer.parseInt(row.get(UbsUserAddressEntityFields.ID.getNumber())))
                .setStatus(row.get(UbsUserAddressEntityFields.STATUS.getNumber()))
                .setStreet(row.get(UbsUserAddressEntityFields.STREET.getNumber()))
                .setUser_id(Integer.parseInt(row.get(UbsUserAddressEntityFields.USER_ID.getNumber())));
    }

    public static List<UbsUserAddressEntity> getListUbsUserAddressEntity(List<List<String>> rows) {
        List<UbsUserAddressEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getUbsUserAddressEntity(currentRow));
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getStreet() {
        return street;
    }

    public int getUser_id() {
        return user_id;
    }

    public UbsUserAddressEntity setId(int id) {
        this.id = id;
        return this;
    }

    public UbsUserAddressEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public UbsUserAddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public UbsUserAddressEntity setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    @Override
    public String toString() {
        return "UbsUserAddressEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", street='" + street + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
