package com.example.paymentplaces.controllers;

import com.example.paymentplaces.dto.merchant.MerchantCreateDTO;
import com.example.paymentplaces.dto.merchant.MerchantUpdateDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.enums.MerchatnStatusEnum;
import com.example.paymentplaces.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/merchant")
public class MerchantConroller implements GenericCrudController<MerchantCreateDTO, MerchantUpdateDTO, Merchant>{

    private final MerchantService merchantService;

    @Override
    @PostMapping("/create1")
    public ResponseEntity<DataDTO<Long>> create(MerchantCreateDTO dto) {
        return merchantService.create(dto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDTO<Long>> update(MerchantUpdateDTO dto) {
        return merchantService.update(dto);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDTO<Boolean>> delete(Long id) {
        return merchantService.delete(id);
    }

    @Override
    @GetMapping("/get")
    public ResponseEntity<DataDTO<List<Merchant>>> getALl() {
        return merchantService.getAll();
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<DataDTO<List<Merchant>>> getAllActive() {
        return merchantService.getAllbyStatus(MerchatnStatusEnum.ACTIVE.name());
    }

    @GetMapping("/getAllPassive")
    public ResponseEntity<DataDTO<List<Merchant>>> getAllPassive() {
        return merchantService.getAllbyStatus(MerchatnStatusEnum.PASSIVE.name());
    }

    @GetMapping("/getAllDeleted")
    public ResponseEntity<DataDTO<List<Merchant>>> getAllDeleted() {
        return merchantService.getAllbyStatus(MerchatnStatusEnum.DELETED.name());
    }

}
