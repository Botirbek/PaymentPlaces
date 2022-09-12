package com.example.paymentplaces.entity;

import lombok.*;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Entity
public class Epos extends BasicEntity{

    private double terminal_id;

    private double merchant_id;
}
