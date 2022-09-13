package com.example.paymentplaces.services;

import com.example.paymentplaces.dto.merchant.MerchantUpdateDTO;
import com.example.paymentplaces.entity.Epos;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.dto.merchant.MerchantCreateDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.repository.MerchantMarketRepository;
import com.example.paymentplaces.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMarketRepository merchantMarketRepository;

    public ResponseEntity<DataDTO<Boolean>> delete(Long id,Integer updatedBy) {
        Optional<Merchant> byId = merchantRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.ok(new DataDTO<>(false));
        }
        byId.get().setStatus(MerchatnStatusEnum.DELETED.name());
        byId.get().setUpdatedBy(updatedBy);
        merchantRepository.save(byId.get());
        return ResponseEntity.ok(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<Long>> create(MerchantCreateDTO dto) {
        List<MerchantMarket> list = new ArrayList<>();

        Merchant merchant = Merchant.createdDtoBuilder()
                .INN(dto.getINN())
                .MFO(dto.getMFO())
                .organizationName(dto.getOrganizationName())
                .phoneNumber(dto.getPhoneNumber())
                .logo(dto.getLogo())
                .createdBy(dto.getCreatedBy())
                .build();

        Merchant save = merchantRepository.save(merchant);

        dto.getMerchantMarketList()
                .forEach(merchantMarketCreateDTO ->
                        list.add((MerchantMarket.createdDtoBuilder()
                                .name(merchantMarketCreateDTO.getName())
                                .address(merchantMarketCreateDTO.getAddress())
                                .latitude(merchantMarketCreateDTO.getLatitude())
                                .longtitude(merchantMarketCreateDTO.getLongtitude())
                                .epos(Epos.builder()
                                        .merchant_id(merchantMarketCreateDTO.getEpos().getMerchant_id())
                                        .terminal_id(merchantMarketCreateDTO.getEpos().getTerminal_id())
                                        .build())
                                .createdBy(merchantMarketCreateDTO.getCreatedBy())
                                .merchant(merchant)
                                .build())
                        ));

        merchant.setMerchantMarketList(list);
        merchantRepository.save(merchant);
        return ResponseEntity.ok(new DataDTO<>(save.getId()));
    }

    public ResponseEntity<DataDTO<Long>> update(MerchantUpdateDTO dto) {
        Optional<Merchant> byId = merchantRepository.findById(dto.getId());

        if (byId.isEmpty()){
            return ResponseEntity.ok(new DataDTO<>(null));
        }

        Merchant merchant = byId.get();
        if (dto.getINN() != 0) merchant.setINN(dto.getINN());
        if (dto.getMFO() != null) merchant.setMFO(dto.getMFO());
        if (dto.getPhoneNumber() != null) merchant.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getLogo() != 0) merchant.setLogo(dto.getLogo());
        if (dto.getOrganizationName() != null) merchant.setOrganizationName(dto.getOrganizationName());

        merchant.setUpdatedBy(dto.getUpdatedBy());
        merchantRepository.save(merchant);
        return ResponseEntity.ok(new DataDTO<>(merchant.getId()));
    }

    public ResponseEntity<DataDTO<List<Merchant>>> getAll() {
        return ResponseEntity.ok(new DataDTO<>(merchantRepository.findAll()));
    }

    public ResponseEntity<DataDTO<List<Merchant>>> getAllbyStatus(String status) {
        return ResponseEntity.ok(new DataDTO<>(merchantRepository.findAllByStatus(status)));
    }

    public ResponseEntity<DataDTO<Merchant>> getById(Long id) {
        Merchant merchant = merchantRepository.findById(id).get();
        return ResponseEntity.ok(new DataDTO<>(merchant));
    }
}
