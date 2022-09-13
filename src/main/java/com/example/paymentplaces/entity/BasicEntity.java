package com.example.paymentplaces.entity;

import com.example.paymentplaces.enums.MerchatnStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
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

    protected String status = MerchatnStatusEnum.ACTIVE.name();

    @Column(updatable = false)
    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Column(updatable = false)
    @CreatedBy
    protected  int createdBy;

    @LastModifiedDate
    protected  int updatedBy;

}
