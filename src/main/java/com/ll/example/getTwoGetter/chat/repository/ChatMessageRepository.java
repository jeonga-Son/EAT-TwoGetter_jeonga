package com.ll.example.getTwoGetter.chat.repository;

import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
//    List<ChatMessage> findByChatInfo(int id);

    ChatMessage findByUsername(String username);
}
