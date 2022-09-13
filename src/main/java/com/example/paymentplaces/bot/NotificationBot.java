package com.example.paymentplaces.bot;

import com.example.paymentplaces.bot.entity.BotUser;
import com.example.paymentplaces.bot.enums.UserStateEnum;
import com.example.paymentplaces.bot.service.BotUserService;
import com.example.paymentplaces.entity.Merchant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class NotificationBot extends TelegramLongPollingBot {

    String BOT_TOKEN = "5392540866:AAFTqmex_jx_kes547_ZiwvvroTBVO7NJ1o";
    String BOT_USERNAME = "http://t.me/microservice_notification_bot";

    private final String SMS_BOT ="\uD83D\uDCB8 Операция\n" +
            "\uD83D\uDCB0 1.270.000,00 UZS\n" +
            "\uD83D\uDCCD Merchant Market Name\n" +
            "\uD83D\uDCB3 HUMOCARD *6649\n" +
            "\uD83D\uDD53 23:36 12.09.2022\n" +
            "\uD83D\uDC68\u200D\uD83E\uDDB0 Client name\n";

    @Autowired  BotUserService botUserService;


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasCallbackQuery() && !update.hasMessage())
            return;

        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            send(SMS_BOT, chatId);
//            BotUser botUser = botUserService.existUser(chatId);
//
//            if (message.equals("/start")) {
//                if (botUser == null) {
//                    registerMerchant(update, botUser);
//                } else {
//                    Merchant merchant = botUserService.getById(botUser.getMerchantId());
//                    send("Assalomu alaykum, " + merchant.getOrganizationName(), chatId);
//                    botUser.setUserStateEnum(UserStateEnum.WORK_STATE);
//                    botUserService.save(botUser);
//                }
//            }
        }
    }

    private void registerMerchant(Update update, BotUser botUser) {
        botUser.setUserStateEnum(UserStateEnum.SHARE_CONTACT);
    }

    public void send(ReplyKeyboardMarkup menu, String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(menu);

        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(InlineKeyboardMarkup menu, String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(menu);

        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
