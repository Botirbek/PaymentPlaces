package com.example.paymentplaces.dto.merchant;

import com.example.paymentplaces.dto.base.BaseDTO;
import com.example.paymentplaces.dto.merchantMarket.MerchantMarketCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantCreateDTO implements BaseDTO {

    @NotBlank(message = "Organization Name cannot be blank")
    private String organizationName;

    @NotBlank(message = "MFO cannot be blank")
    private String MFO;

    @NotBlank(message = "INN cannot be blank")
    private int INN;

    @NotBlank(message = "Phone Number cannot be blank")
    private String phoneNumber;

    private int logo;

    private Integer createdBy;

    private List<MerchantMarketCreateDTO> merchantMarketList;
}
