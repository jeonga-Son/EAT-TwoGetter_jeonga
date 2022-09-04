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

    //채팅방 정보에 해당하는 채팅 메시지를 찾는다
    public List<ChatMessage> findByChatInfo(ChatInfo chatInfo) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatInfo(chatInfo);
        return chatMessages;
    }

    //채팅 메시를 저장한다.
    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
