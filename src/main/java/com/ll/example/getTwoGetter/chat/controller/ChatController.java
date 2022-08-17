package com.ll.example.getTwoGetter.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping(value = "/list")
    public String chatList(){
        return "chat/chatList";
    }

}
