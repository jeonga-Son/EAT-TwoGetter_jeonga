package com.ll.example.getTwoGetter.chat.repository;

import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
