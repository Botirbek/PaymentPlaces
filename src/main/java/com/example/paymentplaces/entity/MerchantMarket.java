package com.example.paymentplaces.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "merchant_market")
public class MerchantMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private Yepost yepost;

    private String status;

    @Column(name = "longtitude", nullable = false)
    private double longtitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    public MerchantMarket(String name, String address, String status, double longtitude, double latitude) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

}