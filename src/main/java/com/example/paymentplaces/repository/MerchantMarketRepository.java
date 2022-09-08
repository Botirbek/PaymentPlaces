package com.example.paymentplaces.repository;

import com.example.paymentplaces.entity.MerchantMarket;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMarketRepository  extends JpaRepository<MerchantMarket, Long> {

    @Query("select m from MerchantMarket m where m.status = ?1")
    List<MerchantMarket> findAllByStatus(String status);

}
