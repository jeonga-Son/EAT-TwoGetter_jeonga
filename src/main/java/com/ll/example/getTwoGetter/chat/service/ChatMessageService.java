package com.ll.example.getTwoGetter.chat.service;

import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> findByChatInfo(ChatInfo chatInfo) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatInfo(chatInfo);
        return chatMessages;
    }
}
