package com.example.paymentplaces.services;

import com.example.paymentplaces.bot.NotificationBot;
import com.example.paymentplaces.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final NotificationBot notificationBot;


    public void sendBotSmS(NotificationDTO dto){
        String SMS_BOT = "\uD83D\uDCB8 Операция\n" +
                "\uD83D\uDCB0 "+dto.getSumma()+"\n" +
                "\uD83D\uDCCD "+dto.getMerchatName()+" " +dto.getMarketAddress() +"\n" +
                "\uD83D\uDCB3 "+ dto.getCardNumber() +"\n" +
                "\uD83D\uDD53 "+ dto.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))+"\n" +
                "\uD83D\uDC68\u200D\uD83E\uDDB0 "+dto.getClientName()+"\n";

        notificationBot.send(SMS_BOT, dto.getPhoneNumber());
    }

    public void sendSmS(NotificationDTO dto){
        String SMS = "\uD83D\uDCB8 Операция\n" +
                "\uD83D\uDCB0 "+dto.getSumma()+"\n" +
                "\uD83D\uDCCD "+dto.getMerchatName()+" " +dto.getMarketAddress() +"\n" +
                "\uD83D\uDCB3 "+ dto.getCardNumber() +"\n" +
                "\uD83D\uDD53 "+ dto.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))+"\n" +
                "\uD83D\uDC68\u200D\uD83E\uDDB0 "+dto.getClientName()+"\n";

        // TODO: 9/15/2022 use SMS service API
        //smsService.send(SMS, dto.getPhoneNumber());
    }

}
