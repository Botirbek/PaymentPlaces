package com.example.paymentplaces.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name = "merchant_market")
public class MerchantMarket extends BasicEntity{

    private String name;

    private String address;

    private String status;

    @Column(name = "longtitude", nullable = false)
    private double longtitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @OneToOne
    private Epos epos;

    public MerchantMarket(String name, String address, String status, double longtitude, double latitude) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

}