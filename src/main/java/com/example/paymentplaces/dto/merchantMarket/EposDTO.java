package com.example.paymentplaces.dto.merchantMarket;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EposDTO {
    private double terminal_id;
    private double merchant_id;
}
