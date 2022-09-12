package com.example.paymentplaces.bot.entity;

import com.example.paymentplaces.bot.enums.UserStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  chatId;

    private Long Merchant_id;

    private String phone_number;

    @Enumerated(EnumType.STRING)
    private UserStateEnum userStateEnum;
}
