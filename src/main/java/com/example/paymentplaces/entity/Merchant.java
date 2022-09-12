package com.example.paymentplaces.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper=false)
public class Merchant extends BasicEntity{

    @Column(name = "organization_Name")
    private String organizationName;

    private String MFO;

    @Column(nullable = false)
    private int INN;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    private int logo;

    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MerchantMarket> merchantMarketList;

}
