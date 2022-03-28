package com.ita.edu.greencity.api.models.ubs.client.Adress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Coordinates {
    private double latitude;
    private double longitude;
    public Coordinates() {
    }

}
