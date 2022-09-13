package com.example.paymentplaces.bot.entity;

import com.example.paymentplaces.bot.enums.UserStateEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  chatId;

    private Long MerchantId;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserStateEnum userStateEnum;
}
