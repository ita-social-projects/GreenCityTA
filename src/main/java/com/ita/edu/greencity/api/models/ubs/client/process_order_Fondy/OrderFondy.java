package com.ita.edu.greencity.api.models.ubs.client.process_order_Fondy;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class OrderFondy {
    private final List<String> certificates;
    @NonNull
    private Long orderId;
    @NonNull
    private Long pointsToUse;
}
