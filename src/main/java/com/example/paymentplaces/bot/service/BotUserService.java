package com.example.paymentplaces.bot.service;

import com.example.paymentplaces.bot.entity.BotUser;
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

    public BotUser existUser(String chatId) {
        Optional<BotUser> byChat_id = botUserRepository.findByChatId(chatId);
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

    public void save(BotUser botUser) {
        botUserRepository.save(botUser);
    }
}
