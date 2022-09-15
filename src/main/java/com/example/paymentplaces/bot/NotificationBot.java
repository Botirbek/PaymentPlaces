package com.example.paymentplaces.bot;

import com.example.paymentplaces.bot.entity.UserBot;
import com.example.paymentplaces.bot.enums.UserStateEnum;
import com.example.paymentplaces.bot.service.BotUserService;
import com.example.paymentplaces.entity.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationBot extends TelegramLongPollingBot {
    private final BotUserService botUserService;

    String BOT_TOKEN = "5392540866:AAFTqmex_jx_kes547_ZiwvvroTBVO7NJ1o";
    String BOT_USERNAME = "http://t.me/microservice_notification_bot";
    private String chatId;

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
            this.chatId = message.getChatId().toString();

            UserBot userBot = botUserService.existUser(chatId);
            if (userBot == null) {
                userBot = new UserBot();
                userBot.setUserStateEnum(UserStateEnum.START);
            }

            switch (userBot.getUserStateEnum()) {
                case START -> {
                    userBot.setChatId(this.chatId);
                    userBot.setUserStateEnum(UserStateEnum.SHARE_CONTACT);
                    botUserService.save(userBot);
                    shareContact(update);
                }

                case SHARE_CONTACT -> registerUser(update, userBot);

                case WORK_STATE -> {
                    Merchant merchant = botUserService.getById(userBot.getMerchantId());
                    send("Assalomu alaykum, " + merchant.getOrganizationName() + " siz royxatdan o'tgansiz");
                    botUserService.save(userBot);
                }
            }
        }
    }

    private void registerUser(Update update, UserBot userBot) {
        Message message = update.getMessage();
        String phoneNumber = "";
        if (message.hasContact()) {
            phoneNumber = message.getContact().getPhoneNumber();
        } else if (message.hasText()) {
            phoneNumber = message.getText();
        }

        if (userBot.getPhoneNumber()!=null && userBot.getPhoneNumber().equals(phoneNumber)) {
            userBot.setChatId(this.chatId);
            userBot.setUserStateEnum(UserStateEnum.WORK_STATE);
            botUserService.save(userBot);
            send("Bot uchun ro'yxtadan utdingiz");
        }else {
            send("Bu nomer Merchant sifatida royxatdan utmagan");
            userBot.setUserStateEnum(UserStateEnum.SHARE_CONTACT);
            botUserService.save(userBot);
//            shareContact(update);
        }
    }

    private void shareContact(Update update) {
        Message message = update.hasMessage() ? update.getMessage() : update.getCallbackQuery().getMessage();
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), message.getChat().getFirstName() + "   ENTER_PHONE_NUMBER_TEXT");
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        markup.setKeyboard(rowList);
        markup.setResizeKeyboard(true);

        KeyboardButton number = new KeyboardButton("Share Contact");
        number.setRequestContact(true);
        KeyboardButton back = new KeyboardButton("Back");
        rowList.add(new KeyboardRow(Collections.singletonList(number)));
        rowList.add(new KeyboardRow(Collections.singletonList(back)));

        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(false);
        send(markup, "Royxatdan o'tish uchun merchantga biriktirilgan tel nomer jo'nating");
    }

    public void send(ReplyKeyboardMarkup menu, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(this.chatId);
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

    public void send(String text, String phoneNumber) {
        UserBot user = botUserService.findByPhoneNumber(phoneNumber);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(text);

        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(this.chatId);
        sendMessage.setText(text);

        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
