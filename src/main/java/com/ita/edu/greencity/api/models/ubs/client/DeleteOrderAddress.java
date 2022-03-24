package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

@Data
public class DeleteOrderAddress {

    private Boolean actual;
    private String addressComment;
    private String city;
    private String cityEn;
    private Double latitude;
    private Double longitude;
    private String district;
    private String districtEn;
    private String entranceNumber;
    private String houseCorpus;
    private String houseNumber;
    private Long id;
    private String region;
    private String regionEn;
    private String street;
    private String streetEn;

}
