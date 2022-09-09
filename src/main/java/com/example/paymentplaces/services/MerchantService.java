package com.example.paymentplaces.services;

import com.example.paymentplaces.dto.merchant.MerchantUpdateDTO;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.dto.merchant.MerchantCreateDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.Merchant;
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

    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        Optional<Merchant> byId = merchantRepository.findById(id);
        if (byId.isEmpty()){
            return ResponseEntity.ok(new DataDTO<>(false));
        }
        byId.get().setStatus(MerchatnStatusEnum.DELETED.name());
        merchantRepository.save(byId.get());
        return ResponseEntity.ok(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<Long>> create(MerchantCreateDTO dto) {
        List<MerchantMarket> list = new ArrayList<>();
        dto.getMerchantMarketList()
                .forEach(merchantMarketCreateDTO ->
                        list.add(new MerchantMarket(
                                merchantMarketCreateDTO.getName(),
                                merchantMarketCreateDTO.getAddress(),
                                MerchatnStatusEnum.ACTIVE.name(),
                                merchantMarketCreateDTO.getLatitude(),
                                merchantMarketCreateDTO.getLongtitude()
                        )));

        Merchant merchant = Merchant.builder()
                .INN(dto.getINN())
                .MFO(dto.getMFO())
                .organizationName(dto.getOrganizationName())
                .phoneNumber(dto.getPhoneNumber())
                .status(MerchatnStatusEnum.ACTIVE.name())
                .merchantMarketList(list)
                .logo(dto.getLogo())
                .build();

        Merchant save = merchantRepository.save(merchant);
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

        merchantRepository.save(merchant);

        return ResponseEntity.ok(new DataDTO<>(merchant.getId()));
    }

    public ResponseEntity<DataDTO<List<Merchant>>> getAll() {
        return ResponseEntity.ok(new DataDTO<>(merchantRepository.findAll()));
    }

    public ResponseEntity<DataDTO<List<Merchant>>> getAllbyStatus(String status) {
        return ResponseEntity.ok(new DataDTO<>(merchantRepository.findAllByStatus(status)));
    }

}
