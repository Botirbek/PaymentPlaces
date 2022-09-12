package com.example.paymentplaces.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    @CreatedDate
    LocalDateTime createdDate;

    @UpdateTimestamp
    @LastModifiedDate
    LocalDateTime updatedDate;

    @Column(updatable = false)
    @CreatedBy
    int createdBy;

    @LastModifiedDate
    int updatedBy;

}
