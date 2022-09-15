package com.example.paymentplaces.bot.service;

import com.example.paymentplaces.bot.entity.UserBot;
import com.example.paymentplaces.bot.repository.BotUserRepository;
import com.example.paymentplaces.entity.Merchant;
import com.example.paymentplaces.repository.MerchantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotUserService {

    private final BotUserRepository botUserRepository;
    private final MerchantRepository merchantRepository;

    public UserBot existUser(String chatId) {
        Optional<UserBot> byChat_id = botUserRepository.findByChatId(chatId);
        if (byChat_id.isPresent()) return byChat_id.get();
        return null;
    }

    public Merchant getById(Long merchant_id) {
        Optional<Merchant> byId = merchantRepository.findById(merchant_id);
        if (byId.isEmpty()){
            return null;
        }
        return byId.get();
    }

    public void save(UserBot userBot) {
        botUserRepository.save(userBot);
    }

    public UserBot findByPhoneNumber(String phoneNumber) {
        Optional<UserBot> byChat_id =  botUserRepository.findByPhoneNumber(phoneNumber);
        if (byChat_id.isPresent()) return byChat_id.get();
        return null;
    }
}
