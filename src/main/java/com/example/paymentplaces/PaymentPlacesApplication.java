package com.example.paymentplaces;

import com.example.paymentplaces.bot.NotificationBot;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@OpenAPIDefinition
public class PaymentPlacesApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(PaymentPlacesApplication.class, args);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new NotificationBot());
    }


}
