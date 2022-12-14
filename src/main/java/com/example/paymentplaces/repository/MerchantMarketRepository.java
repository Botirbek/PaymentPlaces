package com.example.paymentplaces.repository;

import com.example.paymentplaces.entity.MerchantMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface MerchantMarketRepository  extends JpaRepository<MerchantMarket, Long> {

    @Query("select m from MerchantMarket m where m.status = ?1")
    List<MerchantMarket> findAllByStatus(String status);

    @Query(nativeQuery = true, value = "select t.id              market_id,\n" +
            "       m.id                                       merchant_id,\n" +
            "       m.organization_name,\n" +
            "       t.name,\n" +
            "       t.address,\n" +
            "       SQRT(\n" +
            "                   POW(69.1 * (t.latitude - ?1), 2) +\n" +
            "                   POW(69.1 * (?2 - t.longtitude) * COS(t.latitude / 57.3), 2)) AS distance\n" +
            "from merchant_market t,\n" +
            "     merchant m\n" +
            "where t.status = 'ACTIVE'\n" +
            "  and m.id = t.merchant_id\n" +
            "order by distance asc;")
    List<Tuple> findNearMarkets(Double lan, Double lon);

}
