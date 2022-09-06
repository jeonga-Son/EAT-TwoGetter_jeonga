package com.ll.example.getTwoGetter.chat.controller;


import com.ll.example.getTwoGetter.chat.dto.ChatInfoDto;
import com.ll.example.getTwoGetter.chat.dto.ChatMessageDto;
import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.model.ChatMessage;
import com.ll.example.getTwoGetter.chat.repository.ChatInfoRepository;
import com.ll.example.getTwoGetter.chat.repository.ChatMessageRepository;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return chatMessages;
    }
    @PostMapping("/sendMessage/{id}")
    public void sendMessage(@PathVariable long id, @RequestBody ChatMessageDto chatMessageDto){
        ChatInfo chatInfo = chatInfoService.findById(Long.parseLong(chatMessageDto.getChatId()));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDto.getChatContent());
        chatMessage.setChatInfo(chatInfo);
        chatMessage.setUsername(chatMessageDto.getNickname());
        chatMessage.setChatMessageTime(LocalDateTime.now());

        chatMessageService.save(chatMessage);

    }
    @PostMapping("/chatPost")
    public void sendChatPost(@RequestBody ChatInfoDto chatInfoDto){
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setChatTitle(chatInfoDto.getChatTitle());
        chatInfo.setCreateChatDate(LocalDateTime.now());
        chatInfo.setUsername(chatInfoDto.getUsername());
        chatInfo.setPartner(chatInfoDto.getPartner());
        chatInfoService.save(chatInfo);
    }

    @DeleteMapping("/chatDelete/{id}")
    public void chatDelete(@PathVariable long id){
        System.out.println(id);
        ChatInfo chatInfo = chatInfoService.findById(id);
        chatInfoService.delete(chatInfo);
    }
}
