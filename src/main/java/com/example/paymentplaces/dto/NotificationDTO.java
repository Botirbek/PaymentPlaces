package com.example.paymentplaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private String clientName;
    private String phoneNumber;
    private String cardNumber;
    private String merchatName;  // merchat name + market name
    private String marketAddress;
    private Double summa;
    private LocalDateTime dateTime;
}
