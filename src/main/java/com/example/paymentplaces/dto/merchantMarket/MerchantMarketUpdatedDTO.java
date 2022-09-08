package com.example.paymentplaces.dto.merchantMarket;

import com.example.paymentplaces.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantMarketUpdatedDTO implements BaseDTO {

    private Long id;
    private String name;
    private String address;
    private double longtitude;
    private double latitude;

}
