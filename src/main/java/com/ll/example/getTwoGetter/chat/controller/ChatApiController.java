package com.ll.example.getTwoGetter.chat.controller;


import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatInfoRepository;
import com.ll.example.getTwoGetter.chat.repository.ChatMessageRepository;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatApi")
public class ChatApiController {
    @Autowired
    ChatInfoService chatInfoService;
    @Autowired
    ChatMessageService chatMessageService;

    @GetMapping("/chatMessage/{id}")
    public List<ChatMessage> showChatMessage(@PathVariable("id") long id){
        ChatInfo chatInfo = chatInfoService.findById(id);
        List<ChatMessage> chatMessages = chatMessageService.findByChatInfo(chatInfo);
        System.out.println(chatMessages);
        return chatMessages;
    }
}
