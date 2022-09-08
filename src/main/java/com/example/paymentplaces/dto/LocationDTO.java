package com.example.paymentplaces.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDTO {
    private double latitude;
    private double longtitude;
}
