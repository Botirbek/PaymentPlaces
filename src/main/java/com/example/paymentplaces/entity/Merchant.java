package com.example.paymentplaces.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties({"createdDate","updatedDate", "createdBy", "updatedBy"})
public class Merchant extends BasicEntity{

    @Column(name = "organization_Name")
    private String organizationName;

    private String MFO;

    @Column(nullable = false)
    private int INN;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    private int logo;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MerchantMarket> merchantMarketList;


    @Builder(builderMethodName = "createdDtoBuilder")
    public Merchant(String organizationName, String MFO, int INN, String phoneNumber, int logo, List<MerchantMarket> merchantMarketList, Integer createdBy) {
        this.organizationName = organizationName;
        this.MFO = MFO;
        this.INN = INN;
        this.phoneNumber = phoneNumber;
        this.logo = logo;
        this.merchantMarketList = merchantMarketList;
        this.createdBy = createdBy;
    }

    @Builder(builderMethodName = "updatedDtoBuilder")
    public Merchant(String organizationName, String MFO, int INN, String phoneNumber, int logo,Integer createdBy,  List<MerchantMarket> merchantMarketList) {
        this.organizationName = organizationName;
        this.MFO = MFO;
        this.INN = INN;
        this.phoneNumber = phoneNumber;
        this.logo = logo;
        this.merchantMarketList = merchantMarketList;
        this.createdBy = createdBy;
    }
}
