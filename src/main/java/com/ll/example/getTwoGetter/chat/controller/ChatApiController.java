package com.ll.example.getTwoGetter.chat.controller;


import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatApi")
public class ChatApiController {

//    @Autowired
//    ChatMessageRepository chatMessageRepository;

//    @GetMapping("/chatMessage/{id}")
//    public List<ChatMessage> showChatMessage(@PathVariable("id") int id){
//        System.out.println("dd");
//        List<ChatMessage> chatMessages = chatMessageRepository.findByChatInfo(id);
//        return chatMessages;
//    }
//    @GetMapping("/chatMessage/{id}")
//    public ChatMessage showChatMessage(@PathVariable("id") String username){
//        System.out.println("dd");
//        ChatMessage chatMessages = chatMessageRepository.findByUsername(username);
//        return chatMessages;
//    }
}
