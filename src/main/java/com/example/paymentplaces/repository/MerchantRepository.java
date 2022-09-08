package com.example.paymentplaces.repository;

import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.entity.MerchantMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query("select m from Merchant m where m.status = ?1")
    List<Merchant> findAllByStatus(String status);

}
