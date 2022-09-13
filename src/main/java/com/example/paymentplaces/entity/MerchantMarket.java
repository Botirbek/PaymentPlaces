package com.example.paymentplaces.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper=false)
@Entity
@JsonIgnoreProperties({"merchant", "createdDate","updatedDate", "createdBy", "updatedBy"})
@Table(name = "merchant_market")
public class MerchantMarket extends BasicEntity{

    private String name;

    private String address;

    @Column(name = "longtitude", nullable = false)
    private double longtitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Epos epos;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @Builder(builderMethodName = "createdDtoBuilder")
    public MerchantMarket(String name, String address, double longtitude, double latitude, Epos epos, Integer createdBy, Merchant merchant) {
        this.name = name;
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.epos = epos;
        this.createdBy = createdBy;
        this.merchant = merchant;
    }

    @Builder(builderMethodName = "updatedDtoBuilder")
    public MerchantMarket(String name, String address, double longtitude, double latitude, Integer updatedBy, Epos epos) {
        this.name = name;
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.epos = epos;
        this.updatedBy = updatedBy;
    }

}