package com.example.paymentplaces.controllers;

import com.example.paymentplaces.dto.merchantMarket.MerchantMarketCreateDTO;
import com.example.paymentplaces.dto.merchantMarket.MerchantMarketUpdatedDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.services.MerchantMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/merchant/market")
public class MerchantMarketConroller implements GenericCrudController<MerchantMarketCreateDTO, MerchantMarketUpdatedDTO, MerchantMarket> {

    private final MerchantMarketService merchantMarketService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDTO<Long>> create(MerchantMarketCreateDTO dto) {
        return merchantMarketService.create(dto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDTO<Long>> update(MerchantMarketUpdatedDTO dto) {
        return merchantMarketService.update(dto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return merchantMarketService.delete(id);
    }

    @Override
    @GetMapping("/get")
    public ResponseEntity<DataDTO<List<MerchantMarket>>> getALl() {
        return merchantMarketService.getAll();
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<DataDTO<List<MerchantMarket>>> getAllActive() {
        return merchantMarketService.getAllbyStatus(MerchatnStatusEnum.ACTIVE.name());
    }

    @GetMapping("/getAllPassive")
    public ResponseEntity<DataDTO<List<MerchantMarket>>> getAllPassive() {
        return merchantMarketService.getAllbyStatus(MerchatnStatusEnum.PASSIVE.name());
    }

    @GetMapping("/getAllDeleted")
    public ResponseEntity<DataDTO<List<MerchantMarket>>> getAllDeleted() {
        return merchantMarketService.getAllbyStatus(MerchatnStatusEnum.DELETED.name());
    }
}
