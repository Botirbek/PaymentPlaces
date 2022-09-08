package com.example.paymentplaces.dto.merchant;

import com.example.paymentplaces.dto.base.BaseDTO;
import com.example.paymentplaces.entity.MerchantMarket;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantUpdateDTO implements BaseDTO {

    private Long id;

    private String organizationName;

    private String MFO;

    private int INN;

    private String phoneNumber;

    private int logo;

}
