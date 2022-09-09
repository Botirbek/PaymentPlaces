package com.example.paymentplaces.bot.repository;

import com.example.paymentplaces.bot.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotUserRepository extends JpaRepository<BotUser, Long> {

    Optional<BotUser> findByChat_id(String chatId);

}
