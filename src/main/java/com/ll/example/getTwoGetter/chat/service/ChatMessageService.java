package com.ll.example.getTwoGetter.chat.service;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
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

    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    public void modify(String beforeNickname, String afterNickname) {
        List<ChatMessage> chatMessages = chatMessageRepository.findAll();
        if(chatMessages ==null){
            return;
        }
        for(int i=0; i<chatMessages.size(); i++){
            ChatMessage chatMessage = chatMessages.get(i);
            if(chatMessage.getUsername().equals(beforeNickname)){
                chatMessage.setUsername(afterNickname);
                chatMessageRepository.save(chatMessage);
            }
        }
    }
}
