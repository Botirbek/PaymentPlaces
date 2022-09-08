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


    @Query(nativeQuery = true, value = "select SQRT(\n" +
            "                   POW(69.1 * (latitude - ?1), 2) +\n" +
            "                   POW(69.1 * (?2- longtitude) * COS(latitude / 57.3), 2)) AS distance,*\n" +
            "from merchant_market\n" +
            "where status = 'ACTIVE'\n" +
            "order by distance asc ;")
    List<MerchantMarket> findNearMarkets(Double lan1, Double lon1);

}
