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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createdDate","updatedDate", "createdBy", "updatedBy"})
public class Epos extends BasicEntity {

    private double terminal_id;
    private double merchant_id;

}
