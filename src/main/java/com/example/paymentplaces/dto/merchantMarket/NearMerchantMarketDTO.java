package com.example.paymentplaces.dto.merchantMarket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearMerchantMarketDTO {
    private Long market_id;
    private Long merchant_id;
    private String organization_name;
    private String name;
    private String address;
    private double distance;

}
