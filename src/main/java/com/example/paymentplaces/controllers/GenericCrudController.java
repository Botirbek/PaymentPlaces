package com.example.paymentplaces.controllers;

import com.example.paymentplaces.dto.base.BaseDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    UD = updated DTO
    CD = created DTO
    R  = object type for read
*/


public interface GenericCrudController<CD extends BaseDTO, UD extends BaseDTO, R> {

    ResponseEntity<DataDTO<Long>> create (CD dto);
    ResponseEntity<DataDTO<Long>> update (UD dto);
    ResponseEntity<DataDTO<Boolean>> delete (Long id);

    ResponseEntity<DataDTO<List<R>>> getALl();
}
