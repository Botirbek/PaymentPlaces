package com.example.paymentplaces.services;

import com.example.paymentplaces.dto.LocationDTO;
import com.example.paymentplaces.dto.merchantMarket.MerchantMarketCreateDTO;
import com.example.paymentplaces.dto.merchantMarket.MerchantMarketUpdatedDTO;
import com.example.paymentplaces.dto.merchantMarket.NearMerchantMarketDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.Epos;
import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.repository.MerchantMarketRepository;
import com.example.paymentplaces.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantMarketService {

    private final MerchantMarketRepository merchantMarketRepository;
    private final MerchantRepository merchantRepository;

    public ResponseEntity<DataDTO<Boolean>> delete(Long id, Integer updatedBy) {
        Optional<MerchantMarket> byId = merchantMarketRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.ok(new DataDTO<>(false));
        }
        byId.get().setStatus(MerchatnStatusEnum.DELETED.name());
        byId.get().setUpdatedBy(updatedBy);

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

        Epos epos = Epos.builder()
                .merchant_id(dto.getEpos().getMerchant_id())
                .terminal_id(dto.getEpos().getTerminal_id())
                .build();

        MerchantMarket merchantMarket = MerchantMarket.createdDtoBuilder()
                .address(dto.getAddress())
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longtitude(dto.getLongtitude())
                .epos(epos)
                .createdBy(dto.getCreatedBy())
                .build();

        merchantMarket.setMerchant(merchantRepository.findById(dto.getMerchantId()).get());

        merchantMarketRepository.save(merchantMarket);

        return ResponseEntity.ok(new DataDTO<>(merchantMarket.getId()));
    }

    public ResponseEntity<DataDTO<Long>> update(MerchantMarketUpdatedDTO dto) {
        Optional<MerchantMarket> byId = merchantMarketRepository.findById(dto.getId());

        if (byId.isEmpty()) {
            return ResponseEntity.ok(new DataDTO<>(null));
        }

        MerchantMarket merchantMarket = byId.get();
        if (dto.getAddress() != null) merchantMarket.setAddress(dto.getAddress());
        if (dto.getName() != null) merchantMarket.setName(dto.getName());
        if (dto.getLatitude() != 0) merchantMarket.setLatitude(dto.getLatitude());
        if (dto.getLongtitude() != 0) merchantMarket.setLongtitude(dto.getLongtitude());
        if (dto.getEpos() != null) merchantMarket.setEpos(
                Epos.builder()
                        .merchant_id(dto.getEpos().getMerchant_id())
                        .terminal_id(dto.getEpos().getTerminal_id())
                        .build());

        merchantMarket.setUpdatedBy(dto.getUpdatedBy());
        merchantMarketRepository.save(merchantMarket);

        return ResponseEntity.ok(new DataDTO<>(merchantMarket.getId()));
    }

    public ResponseEntity<DataDTO<List<NearMerchantMarketDTO>>> getNearMarkets(LocationDTO locationDTO) {
        List<Tuple> tupleList = merchantMarketRepository.findNearMarkets(locationDTO.getLatitude(), locationDTO.getLongtitude());

        List<NearMerchantMarketDTO> nearMerchantMarketDTOS = tupleList.stream().map(
                tuple -> new NearMerchantMarketDTO(
                        Long.parseLong(tuple.get(0).toString()),
                        Long.parseLong(tuple.get(1).toString()),
                        tuple.get(2, String.class),
                        tuple.get(3, String.class),
                        tuple.get(4, String.class),
                        Double.parseDouble(tuple.get(5).toString())
                )
        ).toList();

        return ResponseEntity.ok(new DataDTO<>(nearMerchantMarketDTOS));
    }

    public ResponseEntity<DataDTO<MerchantMarket>> getById(Long id) {
        MerchantMarket merchantMarket = merchantMarketRepository.findById(id).get();
        return ResponseEntity.ok(new DataDTO<>(merchantMarket));
    }

}
