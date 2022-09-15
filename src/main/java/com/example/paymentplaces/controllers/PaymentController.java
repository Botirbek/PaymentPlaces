package com.example.paymentplaces.controllers;

import com.example.paymentplaces.dto.NotificationDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/send")
    public ResponseEntity<DataDTO<?>> sendNotification(NotificationDTO dto) {
        return paymentService.sendBotSmS(dto);
        /*
        {
          "merchatId": 93,
          "merketId": 95,
          "clientName": "Botirbek Hamraqulov",
          "phoneNumber": "+998998681802",
          "cardNumber": "9860020101004966",
          "summa": 1542000,
          "dateTime": "15:14 15.09.2022"
        }
         */


    }

}
