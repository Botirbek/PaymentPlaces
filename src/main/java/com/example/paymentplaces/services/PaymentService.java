package com.example.paymentplaces.services;

import com.example.paymentplaces.bot.NotificationBot;
import com.example.paymentplaces.dto.NotificationDTO;
import com.example.paymentplaces.dto.response.DataDTO;
import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.entity.MerchantMarket;
import com.example.paymentplaces.repository.MerchantMarketRepository;
import com.example.paymentplaces.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final NotificationBot notificationBot;
    private final MerchantMarketRepository merchantMarketRepository;
    private final MerchantRepository merchantRepository;


    public ResponseEntity<DataDTO<?>> sendBotSmS(NotificationDTO dto) {

        Optional<Merchant> merchantOptional = merchantRepository.findById(dto.getMerchatId());
        if (merchantOptional.isEmpty()) return ResponseEntity.ok(new DataDTO<>("Merchant not found"));

        Optional<MerchantMarket> merchantMarketOptional = merchantMarketRepository.findById(dto.getMarketId());
        if (merchantMarketOptional.isEmpty()) return ResponseEntity.ok(new DataDTO<>("Market not found"));

        String SMS_BOT = "\uD83D\uDCB8 Операция\n" +
                "\uD83D\uDCB0 " + dto.getSumma() + "\n" +
                "\uD83D\uDCCD " + merchantOptional.get().getOrganizationName() + " "
                                + merchantMarketOptional.get().getName() + ": "
                                + merchantMarketOptional.get().getAddress() + "\n" +
                "\uD83D\uDCB3 " + dto.getCardNumber() + "\n" +
                "\uD83D\uDD53 " + dto.getDateTime() + "\n" +
                "\uD83D\uDC68\u200D\uD83E\uDDB0 " + dto.getClientName() + "\n";

        notificationBot.send(SMS_BOT, dto.getPhoneNumber());

        // TODO: 9/15/2022 use SMS service API

        return  ResponseEntity.ok(new DataDTO<>("SMS sent"));
    }


}
