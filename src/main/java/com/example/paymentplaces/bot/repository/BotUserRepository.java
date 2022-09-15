package com.example.paymentplaces.bot.repository;

import com.example.paymentplaces.bot.entity.UserBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotUserRepository extends JpaRepository<UserBot, Long> {

    Optional<UserBot> findByChatId(String chatId);
    Optional<UserBot> findByPhoneNumber(String phoneNumber);

}
