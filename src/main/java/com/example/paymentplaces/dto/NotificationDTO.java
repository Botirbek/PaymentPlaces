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
    private Long merchatId;
    private Long marketId;
    private String clientName;
    private String phoneNumber;
    private String cardNumber;
    private Double summa;
    private String dateTime;
}
