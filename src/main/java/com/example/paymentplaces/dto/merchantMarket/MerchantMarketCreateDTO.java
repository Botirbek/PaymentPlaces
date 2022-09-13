package com.example.paymentplaces.dto.merchantMarket;


import com.example.paymentplaces.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantMarketCreateDTO implements BaseDTO {

    private Long merchantId;

    @NotBlank(message = "Merchant Market Name cannot be blank")
    private String name;

    @NotBlank(message = "Merchant market Address cannot be blank")
    private String address;

    @NotBlank(message = "Merchant market longtitude cannot be blank")
    private double longtitude;

    @NotBlank(message = "Merchant market latitude cannot be blank")
    private double latitude;

    private EposDTO epos;

    private Integer createdBy;

}
