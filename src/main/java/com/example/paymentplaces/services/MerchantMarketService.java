package com.example.paymentplaces.services;

import com.example.paymentplaces.dto.merchantMarket.MerchantMarketCreateDTO;
import com.example.paymentplaces.dto.merchantMarket.MerchantMarketUpdatedDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.repository.MerchantMarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantMarketService {

    private final MerchantMarketRepository merchantMarketRepository;

    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        Optional<MerchantMarket> byId = merchantMarketRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.ok(new DataDTO<>(false));
        }
        byId.get().setStatus(MerchatnStatusEnum.DELETED.name());

        merchantMarketRepository.save(byId.get());
        return ResponseEntity.ok(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<List<MerchantMarket>>> getAll() {
        return ResponseEntity.ok(new DataDTO<>(merchantMarketRepository.findAll()));
    }

    public ResponseEntity<DataDTO<List<MerchantMarket>>> getAllbyStatus(String status) {
        return ResponseEntity.ok(new DataDTO<>(merchantMarketRepository.findAllByStatus(status)));
    }

    public ResponseEntity<DataDTO<Long>> create(MerchantMarketCreateDTO dto) {
        MerchantMarket build = MerchantMarket.builder()
                .address(dto.getAddress())
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longtitude(dto.getLongtitude())
                .status(MerchatnStatusEnum.ACTIVE.name())
                .build();

        merchantMarketRepository.save(build);

        return ResponseEntity.ok(new DataDTO<>(build.getId()));
    }

    public ResponseEntity<DataDTO<Long>> update(MerchantMarketUpdatedDTO dto) {
        Optional<MerchantMarket> byId = merchantMarketRepository.findById(dto.getId());

        if (byId.isEmpty()){
            return ResponseEntity.ok(new DataDTO<>(null));
        }

        MerchantMarket merchantMarket = byId.get();
        if (dto.getAddress() != null) merchantMarket.setAddress(dto.getAddress());
        if (dto.getName() != null) merchantMarket.setName(dto.getName());
        if (dto.getLatitude() != 0) merchantMarket.setLatitude(dto.getLatitude());
        if (dto.getLongtitude() != 0) merchantMarket.setLongtitude(dto.getLongtitude());

        merchantMarketRepository.save(merchantMarket);

        return ResponseEntity.ok(new DataDTO<>(merchantMarket.getId()));
    }
}
